package xyz.krmentos.adofaigg.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import xyz.krmentos.adofaigg.api.data.ClearData;
import xyz.krmentos.adofaigg.api.data.MapData;
import xyz.krmentos.adofaigg.api.setting.LoadOption;
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
 * <p>데이터를 수동으로 받아오기 위해 {@link #loadMapData()}와 {@link #loadClearData()}를 사용할 수 있습니다.
 *
 * @author Jongyeol
 * @see AdofaiGG
 * @see MapData
 * @see ClearData
 */
public class LoadManager {

    private static String url = "https://docs.google.com/spreadsheets/d/1MOz5cmMpYwpBB95DK1Udcti_8eOrswnxWzFurhAz0yg/gviz/tq?tqx=out:json&tq&gid=";
    private Thread thread;
    private final AdofaiGG adofaiGG;
    MapData[] mapData;
    long lastMapDataLoadTime;
    ClearData[] clearData;
    long lastClearDataLoadTime;

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
                Thread.sleep(adofaiGG.getLoadTime() * 1000);
            }
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * 맵 데이터를 받아오고 배열에 저장합니다.
     */
    public void loadMapData() {
        mapData = MapData.loadData(loadData(MapData.gid));
        lastMapDataLoadTime = System.currentTimeMillis() / 1000;
    }

    /**
     * 클리어 데이터를 받아오고 배열에 저장합니다.
     */
    public void loadClearData() {
        clearData = ClearData.loadData(loadData(ClearData.gid));
        lastClearDataLoadTime = System.currentTimeMillis() / 1000;
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
