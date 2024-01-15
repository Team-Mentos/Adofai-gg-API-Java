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
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.krmentos.adofaigg.query.MapQuery;
import xyz.krmentos.adofaigg.query.UserQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * 이 클래스는 유저 데이터를 나타냅니다.
 *
 * <p>맵 데이터는 Adofai.gg API에서 Google Sheets로부터 로드되어 객체로 매핑됩니다.
 *
 * <p>{@link UserData} 객체를 생성할 때는 {@link #loadData(JsonObject)} 메서드를 사용하여 {@link JsonObject}를 {@link UserData} 배열로 변환합니다.
 *
 * @author Jongyeol
 * @see UserQuery
 * @see Tag
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Setter(AccessLevel.NONE)
public class UserData {
    /**
     * Google Sheet에 GID 코드로 데이터를 불러오는데 사용합니다.
     */
    public static int gid = 151952522;
    private int id;
    private String userName;
    private int rank;
    private double totalPP;
    private String bestRecord;
    private String videoLink;

    /**
     * 주어진 JsonObject에서 {@link UserData} 배열을 생성합니다.
     *
     * @param object Json 데이터에서 {@link JsonObject}로 추출된 데이터입니다.
     * @return {@link UserData} 배열로 변환된 데이터입니다.
     */
    public static UserData[] loadData(JsonObject object) {
        JsonArray rows = object.getAsJsonObject("table").getAsJsonArray("rows");
        UserData[] userDataList = new UserData[rows.size()];
        for(int i = 0; i < rows.size(); i++) {
            try {
                JsonArray data = rows.get(i).getAsJsonObject().getAsJsonArray("c");
                UserData mapData = new UserData();
                mapData.id = getInt(data, 10);
                mapData.userName = getString(data, 9);
                mapData.rank = getInt(data, 8);
                mapData.totalPP = getDouble(data, 11);
                mapData.bestRecord = getString(data, 12);
                mapData.videoLink = getString(data, 13);
                userDataList[mapData.id - 1] = mapData;
            } catch (Exception ignored) {
            }
        }
        return userDataList;
    }

    private static JsonElement getData(JsonArray data, int id) {
        return data.get(id).getAsJsonObject().get("v");
    }

    private static String getString(JsonArray data, int id) {
        return getData(data, id).getAsString();
    }

    private static double getDouble(JsonArray data, int id) {
        return getData(data, id).getAsDouble();
    }

    private static int getInt(JsonArray data, int id) {
        return getData(data, id).getAsInt();
    }
}
