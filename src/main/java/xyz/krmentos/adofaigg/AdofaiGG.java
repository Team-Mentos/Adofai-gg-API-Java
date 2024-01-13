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

import lombok.Getter;
import xyz.krmentos.adofaigg.data.ClearData;
import xyz.krmentos.adofaigg.data.MapData;
import xyz.krmentos.adofaigg.exception.DataNotLoaded;
import xyz.krmentos.adofaigg.query.ClearQuery;
import xyz.krmentos.adofaigg.query.MapQuery;
import xyz.krmentos.adofaigg.setting.LoadOption;

import java.util.Arrays;
import java.util.List;

/**
 * 이 클래스는 Adofai.gg API의 메인 클래스입니다.
 *
 * <p>데이터는 설정된 {@link LoadOption}에 따라 불러오게 되며, 불러오는 시간은 초 단위로 설정됩니다.
 *
 * <p>기본 구성:
 * <ul>
 *     <li>{@link LoadOption} : {@link LoadOption#LOAD_EVERY_ACTIVE}</li>
 *     <li>loadTime : {@code 600}</li>
 * </ul>
 *
 * <p>Adofai.gg API는 {@link AdofaiGG} 객체를 생성한 후 데이터를 불러올 수 있습니다.
 *
 * <p>맵 데이터는 {@link MapQuery}를 통해, 클리어 데이터는 {@link ClearQuery}를 통해 필터를 설정할 수 있습니다.
 *
 * <p>데이터를 수동으로 받기 위해서는 {@link LoadManager}를 참조하세요.
 *
 * @author Jongyeol
 * @see LoadManager
 */
@Getter
public class AdofaiGG {

    private final LoadOption loadOption;
    private final long loadTime;
    private final LoadManager loadManager;

    /**
     * 기본 구성으로 AdofaiGG 개체를 구성합니다.
     */
    public AdofaiGG() {
        this(LoadOption.LOAD_EVERY_ACTIVE);
    }

    /**
     * 설정한 구성과 기본 구성으로 AdofaiGG 개체를 구성합니다.
     *
     * @see LoadOption
     * @param loadOption 데이터를 받아오는 조건을 설정합니다.
     */
    public AdofaiGG(LoadOption loadOption) {
        this(loadOption, 600);
    }

    /**
     * 설정한 구성으로 AdofaiGG 개체를 구성합니다.
     *
     * @see LoadOption
     * @param loadOption 데이터를 받아오는 조건을 설정합니다.
     * @param time 데이터를 받아오는 시간을 초 단위로 설정합니다.
     */
    public AdofaiGG(LoadOption loadOption, long time) {
        this.loadOption = loadOption;
        loadTime = time;
        loadManager = new LoadManager(this);
    }

    /**
     * 설정한 구성으로 AdofaiGG 개체를 구성합니다.
     *
     * @see LoadOption
     * @param loadOption 데이터를 받아오는 조건을 설정합니다.
     * @param minute 데이터를 받아오는 시간을 분 단위로 설정합니다.
     * @param second 데이터를 받아오는 시간을 초 단위로 설정합니다.
     */
    public AdofaiGG(LoadOption loadOption, int minute, int second) {
        this(loadOption, minute * 60L + second);
    }

    /**
     * 설정한 구성으로 AdofaiGG 개체를 구성합니다.
     *
     * @see LoadOption
     * @param loadOption 데이터를 받아오는 조건을 설정합니다.
     * @param hour 데이터를 받아오는 시간을 시간 단위로 설정합니다.
     * @param minute 데이터를 받아오는 시간을 분 단위로 설정합니다.
     * @param second 데이터를 받아오는 시간을 초 단위로 설정합니다.
     */
    public AdofaiGG(LoadOption loadOption, int hour, int minute, int second) {
        this(loadOption, hour * 3600L + minute * 60L + second);
    }

    /**
     * 설정한 구성으로 AdofaiGG 개체를 구성합니다.
     *
     * @see LoadOption
     * @param loadOption 데이터를 받아오는 조건을 설정합니다.
     * @param day 데이터를 받아오는 시간을 일 단위로 설정합니다.
     * @param hour 데이터를 받아오는 시간을 시간 단위로 설정합니다.
     * @param minute 데이터를 받아오는 시간을 분 단위로 설정합니다.
     * @param second 데이터를 받아오는 시간을 초 단위로 설정합니다.
     */
    public AdofaiGG(LoadOption loadOption, int day, int hour, int minute, int second) {
        this(loadOption, day * 86400L + hour * 3600L + minute * 60L + second);
    }

    /**
     * Id를 통해 {@link MapData}를 불러옵니다.
     *
     * <p>예시 사용법:
     * <p>MapData map = getMapById(123);
     *
     * @param id 검색할 {@link MapData}의 식별자입니다.
     * @return 지정된 id와 관련된 {@link MapData} 객체입니다.
     * @throws ArrayIndexOutOfBoundsException 제공된 id가 현재 맵 배열의 범위를 벗어난 경우 예외가 발생합니다.
     * @throws DataNotLoaded 데이터가 로딩되지 않았을 경우 예외가 발생합니다.
     */
    public MapData getMapById(int id) {
        return getMaps()[id - 1];
    }

    /**
     * 지정된 문자열을 포함하는 노래 제목을 가진 모든 {@link MapData}를 반환합니다.
     *
     * @param query 검색할 문자열입니다.
     * @return 검색 조건을 만족하는 {@link MapData} 객체들의 목록입니다.
     * @throws DataNotLoaded 데이터가 로딩되지 않았을 경우 예외가 발생합니다.
     */
    public List<MapData> getMapByName(String query) {
        return Arrays.stream(getMaps()).filter(mapData -> mapData.getSong().contains(query)).toList();
    }

    /**
     * 지정된 {@link MapQuery}에 따라 필터링된 {@link MapData}의 목록을 반환합니다.
     *
     * @param query {@link MapQuery} 객체로 지정된 검색 조건입니다.
     * @return 검색 조건을 만족하는 {@link MapData} 객체들의 목록입니다.
     * @throws DataNotLoaded 데이터가 로딩되지 않았을 경우 예외가 발생합니다.
     */
    public List<MapData> getMapByQuery(MapQuery query) {
        return query.checkMap(getMaps());
    }

    /**
     * 현재 로드된 모든 {@link MapData} 배열을 반환합니다.
     *
     * @return 현재 로드된 모든 {@link MapData} 객체들의 배열입니다.
     * @throws DataNotLoaded 데이터가 로딩되지 않았을 경우 예외가 발생합니다.
     */
    public MapData[] getMaps() {
        if(loadOption == LoadOption.LOAD_EVERY_ACTIVE ||
            (loadOption == LoadOption.LOAD_ACTIVE_FOR_TIME && loadManager.lastMapDataLoadTime + loadTime <= System.currentTimeMillis() / 1000))
            loadManager.loadMapData();
        if(loadManager.mapData == null) throw new DataNotLoaded();
        return loadManager.mapData;
    }

    /**
     * 지정된 Id에 해당하는 {@link ClearData}를 반환합니다.
     *
     * @param id 검색할 {@link ClearData}의 식별자입니다.
     * @return 지정된 id와 관련된 {@link ClearData} 객체입니다.
     * @throws ArrayIndexOutOfBoundsException 제공된 id가 현재 ClearData 배열의 범위를 벗어난 경우 예외가 발생합니다.
     * @throws DataNotLoaded 데이터가 로딩되지 않았을 경우 예외가 발생합니다.
     */
    public ClearData getClearById(int id) {
        return getClears()[id - 1];
    }

    /**
     * 지정된 {@link ClearQuery}에 따라 필터링된 {@link ClearData} 목록을 반환합니다.
     *
     * @param query {@link ClearQuery} 객체로 지정된 검색 조건입니다.
     * @return 검색 조건을 만족하는 {@link ClearData} 객체들의 목록입니다.
     * @throws DataNotLoaded 데이터가 로딩되지 않았을 경우 예외가 발생합니다.
     */
    public List<ClearData> getClearByQuery(ClearQuery query) {
        return query.checkClear(getClears());
    }

    /**
     * 현재 로드된 모든 {@link ClearData} 배열을 반환합니다.
     *
     * @return 현재 로드된 모든 {@link ClearData} 객체들의 배열입니다.
     * @throws DataNotLoaded 데이터가 로딩되지 않았을 경우 예외가 발생합니다.
     */
    public ClearData[] getClears() {
        if(loadOption == LoadOption.LOAD_EVERY_ACTIVE ||
            (loadOption == LoadOption.LOAD_ACTIVE_FOR_TIME && loadManager.lastClearDataLoadTime + loadTime <= System.currentTimeMillis() / 1000))
            loadManager.loadClearData();
        if(loadManager.clearData == null) throw new DataNotLoaded();
        return loadManager.clearData;
    }
}
