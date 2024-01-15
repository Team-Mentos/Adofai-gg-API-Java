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

package xyz.krmentos.adofaigg.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.*;
import xyz.krmentos.adofaigg.query.ClearQuery;

/**
 * 이 클래스는 클리어 데이터를 나타냅니다.
 *
 * <p>클리어 데이터는 Adofai.gg API에서 Google Sheets로부터 로드되어 객체로 매핑됩니다.
 *
 * <p>ClearData 객체를 생성할 때는 {@link #loadData(JsonObject)} 메서드를 사용하여 {@link JsonObject}를 {@link ClearData} 배열로 변환합니다.
 *
 * @author Jongyeol
 * @see ClearQuery
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Setter(AccessLevel.NONE)
public class ClearData {
    /**
     * Google Sheet에 GID 코드로 데이터를 불러오는데 사용합니다.
     */
    public static int gid = 110445676;
    private int id;
    private String timeStamp;
    private String name;
    private int userCode;
    private int mapId;
    private String videoLink;
    private double ra;
    private double accuracy;
    private int speed;
    private double xAccuracy;
    private double playPoint;
    private int localRank;
    private int songRank;
    private int totalRank;
    private int recordCode;
    private int isOverLaped;
    private int isNew;
    private double weighted;
    private String other;
    private float feeling;

    /**
     * 주어진 JsonObject에서 {@link ClearData} 배열을 생성합니다.
     *
     * @param object Json 데이터에서 {@link JsonObject}로 추출된 데이터입니다.
     * @return {@link ClearData} 배열로 변환된 데이터입니다.
     */
    public static ClearData[] loadData(JsonObject object) {
        JsonArray rows = object.getAsJsonObject("table").getAsJsonArray("rows");
        ClearData[] clearDataList = new ClearData[rows.size()];
        for(int i = 0; i < rows.size(); i++) {
            try {
                JsonArray data = rows.get(i).getAsJsonObject().getAsJsonArray("c");
                ClearData clearData = new ClearData();
                clearData.id = getData(data, 0).getAsInt();
                clearData.timeStamp = getData(data, 1).getAsString();
                clearData.name = getData(data, 2).getAsString();
                clearData.userCode = getData(data, 3).getAsInt();
                clearData.mapId = getData(data, 4).getAsInt();
                clearData.videoLink = getData(data, 24).getAsString();
                clearData.ra = getDouble(data, 11);
                clearData.accuracy = getDouble(data, 12) * 100;
                clearData.speed = (int) (getDouble(data, 13) * 100);
                clearData.xAccuracy = getDouble(data, 14) * 100;
                clearData.playPoint = getDouble(data, 15);
                clearData.localRank = getInt(data, 16);
                clearData.songRank = getInt(data, 17);
                clearData.totalRank = getInt(data, 18);
                clearData.recordCode = getInt(data, 19);
                clearData.isOverLaped = getInt(data, 20);
                clearData.isNew = getInt(data, 21);
                clearData.weighted = getDouble(data, 22);
                clearData.other = getString(data, 23);
                clearData.feeling = getFloat(data, 25);
                clearDataList[i] = clearData;
            } catch (Exception ignored) {
            }
        }
        return clearDataList;
    }

    private static JsonElement getData(JsonArray data, int id) {
        JsonElement element = data.get(id);
        return element.isJsonNull() ? null : element.getAsJsonObject().get("v");
    }

    private static String getString(JsonArray data, int id) {
        JsonElement element = getData(data, id);
        return element == null || element.isJsonNull() ? null : element.getAsString();
    }

    private static double getDouble(JsonArray data, int id) {
        JsonElement element = getData(data, id);
        return element == null ? -1 : element.getAsDouble();
    }

    private static int getInt(JsonArray data, int id) {
        JsonElement element = getData(data, id);
        return element == null ? -1 : element.getAsInt();
    }

    private static float getFloat(JsonArray data, int id) {
        JsonElement element = getData(data, id);
        return element == null ? -1 : element.getAsFloat();
    }
}
