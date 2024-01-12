package xyz.krmentos.adofaigg.setting;

import xyz.krmentos.adofaigg.LoadManager;

/**
 * 맵 데이터를 로드하는 옵션을 정의한 열거형입니다.
 *
 * @author Jongyeol
 * @see LoadManager
 */
public enum LoadOption {
    /**
     * 데이터를 불러올 때 마다 데이터를 받아옵니다.
     */
    LOAD_EVERY_ACTIVE,

    /**
     * 특정 시간 마다 데이터를 받아옵니다.
     */
    LOAD_FOR_TIME,

    /**
     * 데이터를 불러올 때 특정 시간이 지나면 데이터를 받아옵니다.
     */
    LOAD_ACTIVE_FOR_TIME,

    /**
     * 데이터를 단 한번 받아온 후 더 이상 받아오지 않습니다.
     */
    LOAD_ONLY_ONCE,

    /**
     * 데이터를 수동으로 받아옵니다.
     */
    NOT_AUTO_LOAD
}

