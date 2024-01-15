/*
 * Copyright (c) 2024, Team Mentos
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the <organization> nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package xyz.krmentos.adofaigg;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import xyz.krmentos.adofaigg.data.ClearData;
import xyz.krmentos.adofaigg.data.MapData;
import xyz.krmentos.adofaigg.data.UserData;
import xyz.krmentos.adofaigg.setting.LoadOption;
import lombok.Cleanup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 이 클래스는 Adofai.gg API에서 맵 과 클리어 데이터를 받아오는 역할을 합니다.
 *
 * <p>기본적으로 Google Sheets를 통해 데이터를 받아오도록 되어 있습니다.
 *
 * <p>{@link AdofaiGG} 객체를 통해 설정된 옵션에 따라 데이터를 주기적으로 로드할 수 있습니다.
 *
 * <p>데이터를 수동으로 받아오기 위해 {@link #loadMapData()}와 {@link #loadClearData()}와 {@link #loadUserData()}를 사용할 수 있습니다.
 *
 * @author Jongyeol
 * @see AdofaiGG
 * @see MapData
 * @see ClearData
 * @see UserData
 */
public class LoadManager {

    private static String url = "https://docs.google.com/spreadsheets/d/1MOz5cmMpYwpBB95DK1Udcti_8eOrswnxWzFurhAz0yg/gviz/tq?tqx=out:json&tq&gid=";
    private Thread thread;
    private final AdofaiGG adofaiGG;
    MapData[] mapData;
    long lastMapDataLoadTime;
    ClearData[] clearData;
    long lastClearDataLoadTime;
    UserData[] userData;
    long lastUserDataLoadTime;

    /**
     * LoadManager 생성자입니다.
     *
     * @param adofaiGG {@link AdofaiGG} 객체를 전달받아 초기화합니다.
     */
    public LoadManager(AdofaiGG adofaiGG) {
        this.adofaiGG = adofaiGG;
        reloadSetting();
    }

    /**
     * 설정을 다시 로드하고, AdofaiGG 객체의 데이터를 받아오는 옵션에 따라 스레드를 시작합니다.
     */
    public void reloadSetting() {
        if(thread != null) {
            thread.interrupt();
            thread = null;
        }
        if(adofaiGG.getLoadOption() == LoadOption.LOAD_FOR_TIME) {
            thread = new Thread(this::loadSchedule, "Adofai.gg-API-LoadThread");
            thread.start();
        }
        if(adofaiGG.getLoadOption() == LoadOption.LOAD_ONLY_ONCE) {
            loadMapData();
            loadClearData();
        }
    }

    /**
     * 주기적으로 데이터를 받아오는 스케줄을 설정합니다.
     */
    private void loadSchedule() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                loadMapData();
                loadClearData();
                loadUserData();
                Thread.sleep(adofaiGG.getLoadTime());
            }
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * 맵 데이터를 받아오고 배열에 저장합니다.
     */
    public void loadMapData() {
        mapData = MapData.loadData(loadData(MapData.gid));
        lastMapDataLoadTime = System.currentTimeMillis();
    }

    /**
     * 클리어 데이터를 받아오고 배열에 저장합니다.
     */
    public void loadClearData() {
        clearData = ClearData.loadData(loadData(ClearData.gid));
        lastClearDataLoadTime = System.currentTimeMillis();
    }

    /**
     * 유저 데이터를 받아오고 배열에 저장합니다.
     */
    public void loadUserData() {
        userData = UserData.loadData(loadData(UserData.gid));
        lastUserDataLoadTime = System.currentTimeMillis();
    }

    /**
     * 지정된 gid에 해당하는 데이터를 로드하여 JsonObject로 반환합니다.
     *
     * @param gid 로드할 데이터의 gid입니다.
     * @return 로드된 데이터를 담고 있는 JsonObject입니다.
     * @throws RuntimeException 데이터 로드 중 IO예외가 발생한 경우 예외가 발생합니다.
     * @throws JsonParseException 데이터가 Json형식이 아닐경우 예외가 발생합니다
     */
    private JsonObject loadData(int gid) {
        try {
            URL url1 = new URL(url + gid);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            @Cleanup InputStream in = connection.getInputStream();
            String string = new String(in.readAllBytes());
            string = string.replace("/*O_o*/\ngoogle.visualization.Query.setResponse(", "").replace(");", "");
            return JsonParser.parseString(string).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
