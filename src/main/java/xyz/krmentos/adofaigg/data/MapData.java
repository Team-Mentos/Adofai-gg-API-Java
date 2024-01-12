package xyz.krmentos.adofaigg.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.*;
import xyz.krmentos.adofaigg.query.MapQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * 이 클래스는 맵 데이터를 나타냅니다.
 *
 * <p>맵 데이터는 Adofai.gg API에서 Google Sheets로부터 로드되어 객체로 매핑됩니다.
 *
 * <p>{@link MapData} 객체를 생성할 때는 {@link #loadData(JsonObject)} 메서드를 사용하여 {@link JsonObject}를 {@link MapData} 배열로 변환합니다.
 *
 * @author Jongyeol
 * @see MapQuery
 * @see Tag
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Setter(AccessLevel.NONE)
public class MapData {
    public static int gid = 739034057;
    private int id;
    private String song;
    private String artist;
    private float difficulty;
    private String creator;
    private String downloadLink;
    private String workshopLink;
    private String videoLink;
    private boolean ew;
    private double bpm;
    private int tiles;
    private Tag[] tags;
    private boolean dlc;
    private String censorReason;

    /**
     * 주어진 JsonObject에서 {@link MapData} 배열을 생성합니다.
     *
     * @param object Json 데이터에서 {@link JsonObject}로 추출된 데이터입니다.
     * @return {@link MapData} 배열로 변환된 데이터입니다.
     */
    public static MapData[] loadData(JsonObject object) {
        JsonArray rows = object.getAsJsonObject("table").getAsJsonArray("rows");
        MapData[] mapDataList = new MapData[rows.size()];
        for(int i = 0; i < rows.size(); i++) {
            try {
                JsonArray data = rows.get(i).getAsJsonObject().getAsJsonArray("c");
                MapData mapData = new MapData();
                mapData.id = getData(data, 0).getAsInt();
                mapData.song = getData(data, 1).getAsString();
                mapData.artist = getData(data, 2).getAsString();
                mapData.difficulty = getData(data, 16).getAsFloat();
                mapData.creator = getData(data, 4).getAsString();
                mapData.downloadLink = getString(data, 18);
                mapData.workshopLink = getString(data, 19);
                mapData.videoLink = getString(data, 20);
                mapData.bpm = getDouble(data, 9);
                mapData.tiles = getInt(data, 10);
                List<Tag> tagList = new ArrayList<>(5);
                for(int i2 = 11; i2 < 16; i2++) {
                    try {
                        tagList.add(Tag.convertTag(getString(data, i2)));
                    } catch (NullPointerException ignored) {
                    }
                }
                mapData.tags = tagList.toArray(new Tag[0]);
                mapData.dlc = getString(data, 17) != null;
                mapData.censorReason = getString(data, 24);
                mapDataList[i] = mapData;
            } catch (Exception ignored) {
            }
        }
        return mapDataList;
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
}
