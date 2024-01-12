package xyz.krmentos.adofaigg.api.query;

import xyz.krmentos.adofaigg.api.data.ClearData;

import java.util.Arrays;
import java.util.List;

/**
 * 이 클래스는 Adofai.gg API에서 사용되는 클리어 데이터를 필터링하기 위한 클래스입니다.
 *
 * <p>{@link ClearQuery}를 사용하여 클리어 데이터를 특정 조건에 맞게 필터링할 수 있습니다.
 *
 * <p>다양한 메서드를 통해 각 필터 조건을 설정하고, {@link #checkClear(ClearData...)} 메서드를 호출하여 필터링된 클리어 데이터를 얻을 수 있습니다.
 *
 * @author Jongyeol
 * @see ClearData
 */
public class ClearQuery {
    private String name;
    private int userCode = -1;
    private int mapId = -1;
    private int minSpeed = -1;
    private int maxSpeed = -1;
    private double minXAccuracy = -1;
    private double maxXAccuracy = -1;
    private double minPlayPoint = -1;
    private double maxPlayPoint = -1;
    private int minLocalRank = -1;
    private int maxLocalRank = -1;
    private int minSongRank = -1;
    private int maxSongRank = -1;
    private int minTotalRank = -1;
    private int maxTotalRank = -1;

    /**
     * 클리어 데이터의 이름을 설정합니다.
     *
     * @param name 설정할 클리어 데이터의 이름입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws NullPointerException 값이 null일 경우 예외가 발생합니다.
     */
    public ClearQuery setName(String name) {
        checkString(name);
        this.name = name;
        return this;
    }

    /**
     * 클리어 데이터의 사용자 코드를 설정합니다.
     *
     * @param userCode 설정할 클리어 데이터의 사용자 코드입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setUserCode(int userCode) {
        checkInt(userCode, "UserCode");
        this.userCode = userCode;
        return this;
    }

    /**
     * 클리어 데이터의 맵 ID를 설정합니다.
     *
     * @param mapId 설정할 클리어 데이터의 맵 ID입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMapId(int mapId) {
        checkInt(mapId, "MapId");
        this.mapId = mapId;
        return this;
    }

    /**
     * 클리어 데이터의 속도를 설정합니다.
     *
     * @param speed 설정할 클리어 데이터의 속도입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setSpeed(int speed) {
        checkSpeed(speed);
        minSpeed = speed;
        maxSpeed = speed;
        return this;
    }

    /**
     * 클리어 데이터의 속도 범위를 설정합니다.
     *
     * @param minSpeed 최소 속도 값입니다.
     * @param maxSpeed 최대 속도 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public ClearQuery setSpeed(int minSpeed, int maxSpeed) {
        checkSpeed(minSpeed);
        checkSpeed(maxSpeed);
        if(minSpeed > maxSpeed) throw new IllegalArgumentException("maxSpeed cannot be less than the minSpeed");
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        return this;
    }

    /**
     * 클리어 데이터의 최소 속도 범위를 설정합니다.
     *
     * @param minSpeed 최소 속도 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMinSpeed(int minSpeed) {
        checkSpeed(minSpeed);
        this.minSpeed = minSpeed;
        return this;
    }

    /**
     * 클리어 데이터의 최대 속도 범위를 설정합니다.
     *
     * @param maxSpeed 최대 속도 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMaxSpeed(int maxSpeed) {
        checkSpeed(maxSpeed);
        this.maxSpeed = maxSpeed;
        return this;
    }

    private void checkSpeed(int speed) {
        checkInt(speed, "speed");
    }

    /**
     * 클리어 데이터의 절대 정확도를 설정합니다.
     *
     * @param xAccuracy 설정할 클리어 데이터의 절대 정확도입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 값이 100보다 클경우 경우 예외가 발생합니다.
     */
    public ClearQuery setXAccuracy(double xAccuracy) {
        checkXAccuracy(xAccuracy);
        minXAccuracy = xAccuracy;
        maxXAccuracy = xAccuracy;
        return this;
    }

    /**
     * 클리어 데이터의 절대 정확도 범위를 설정합니다.
     *
     * @param minXAccuracy 설정할 절대 정확도의 최소값입니다.
     * @param maxXAccuracy 설정할 절대 정확도의 최대값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 값이 100보다 크거나 최소값이 최대값보다 큰 경우 예외가 발생합니다.
     */
    public ClearQuery setXAccuracy(double minXAccuracy, double maxXAccuracy) {
        checkXAccuracy(minXAccuracy);
        checkXAccuracy(maxXAccuracy);
        if(minXAccuracy > maxXAccuracy)
            throw new IllegalArgumentException("maxXAccuracy cannot be less than the minXAccuracy");
        this.minXAccuracy = minXAccuracy;
        this.maxXAccuracy = maxXAccuracy;
        return this;
    }

    /**
     * 클리어 데이터의 최소 절대 정확도 범위를 설정합니다.
     *
     * @param minXAccuracy 최소 절대 정확도 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 값이 100보다 클경우 경우 예외가 발생합니다.
     */
    public ClearQuery setMinXAccuracy(double minXAccuracy) {
        checkXAccuracy(minXAccuracy);
        this.minXAccuracy = minXAccuracy;
        return this;
    }

    /**
     * 클리어 데이터의 최대 절대 정확도 범위를 설정합니다.
     *
     * @param maxXAccuracy 최대 절대 정확도 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 값이 100보다 클경우 경우 예외가 발생합니다.
     */
    public ClearQuery setMaxXAccuracy(double maxXAccuracy) {
        checkXAccuracy(maxXAccuracy);
        this.maxXAccuracy = maxXAccuracy;
        return this;
    }

    private void checkXAccuracy(double xAccuracy) {
        if(xAccuracy < 0) throw new IllegalArgumentException("XAccuracy cannot be less than 0");
        if(xAccuracy > 100) throw new IllegalArgumentException("XAccuracy cannot be greater than 100");
    }

    /**
     * 클리어 데이터의 PP를 설정합니다.
     *
     * @param playPoint 설정할 클리어 데이터의 PP입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setPlayPoint(double playPoint) {
        checkPlayPoint(playPoint);
        minPlayPoint = playPoint;
        maxPlayPoint = playPoint;
        return this;
    }

    /**
     * 클리어 데이터의 PP 범위를 설정합니다.
     *
     * @param minPlayPoint 최소 PP 값입니다.
     * @param maxPlayPoint 최대 PP 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public ClearQuery setPlayPoint(double minPlayPoint, double maxPlayPoint) {
        checkPlayPoint(minPlayPoint);
        checkPlayPoint(maxPlayPoint);
        if(minPlayPoint > maxPlayPoint)
            throw new IllegalArgumentException("maxPlayPoint cannot be less than the minPlayPoint");
        this.minPlayPoint = minPlayPoint;
        this.maxPlayPoint = maxPlayPoint;
        return this;
    }

    /**
     * 클리어 데이터의 최소 PP 범위를 설정합니다.
     *
     * @param minPlayPoint 최소 PP 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMinPlayPoint(double minPlayPoint) {
        checkPlayPoint(minPlayPoint);
        this.minPlayPoint = minPlayPoint;
        return this;
    }

    /**
     * 클리어 데이터의 최대 PP 범위를 설정합니다.
     *
     * @param maxPlayPoint 최대 PP 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMaxPlayPoint(double maxPlayPoint) {
        checkPlayPoint(maxPlayPoint);
        this.maxPlayPoint = maxPlayPoint;
        return this;
    }

    private void checkPlayPoint(double playPoint) {
        if(playPoint < 0) throw new IllegalArgumentException("PlayPoint cannot be less than 0");
    }

    /**
     * 클리어 데이터의 LocalRank를 설정합니다.
     *
     * @param localRank 설정할 클리어 데이터의 LocalRank입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setLocalRank(int localRank) {
        checkLocalRank(localRank);
        this.minLocalRank = localRank;
        this.maxLocalRank = localRank;
        return this;
    }

    /**
     * 클리어 데이터의 LocalRank 범위를 설정합니다.
     *
     * @param minLocalRank 최소 LocalRank 값입니다.
     * @param maxLocalRank 최대 LocalRank 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public ClearQuery setLocalRank(int minLocalRank, int maxLocalRank) {
        checkLocalRank(minLocalRank);
        checkLocalRank(maxLocalRank);
        if(minLocalRank > maxLocalRank)
            throw new IllegalArgumentException("maxLocalRank cannot be less than the minLocalRank");
        this.minLocalRank = minLocalRank;
        this.maxLocalRank = maxLocalRank;
        return this;
    }

    /**
     * 클리어 데이터의 최소 LocalRank 범위를 설정합니다.
     *
     * @param minLocalRank 최소 LocalRank 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMinLocalRank(int minLocalRank) {
        checkLocalRank(minLocalRank);
        this.minLocalRank = minLocalRank;
        return this;
    }

    /**
     * 클리어 데이터의 최대 LocalRank 범위를 설정합니다.
     *
     * @param maxLocalRank 최대 LocalRank 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMaxLocalRank(int maxLocalRank) {
        checkLocalRank(maxLocalRank);
        this.maxLocalRank = maxLocalRank;
        return this;
    }

    private void checkLocalRank(int localRank) {
        checkInt(localRank, "LocalRank");
    }

    /**
     * 클리어 데이터의 SongRank를 설정합니다.
     *
     * @param songRank 설정할 클리어 데이터의 SongRank입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setSongRank(int songRank) {
        checkSongRank(songRank);
        this.minSongRank = songRank;
        this.maxSongRank = songRank;
        return this;
    }

    /**
     * 클리어 데이터의 SongRank 범위를 설정합니다.
     *
     * @param minSongRank 최소 SongRank 값입니다.
     * @param maxSongRank 최대 SongRank 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public ClearQuery setSongRank(int minSongRank, int maxSongRank) {
        checkSongRank(minSongRank);
        checkSongRank(maxSongRank);
        if(minSongRank > maxSongRank)
            throw new IllegalArgumentException("maxSongRank cannot be less than the minSongRank");
        this.minSongRank = minSongRank;
        this.maxSongRank = maxSongRank;
        return this;
    }

    /**
     * 클리어 데이터의 최소 SongRank 범위를 설정합니다.
     *
     * @param minSongRank 최소 SongRank 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMinSongRank(int minSongRank) {
        checkSongRank(minSongRank);
        this.minSongRank = minSongRank;
        return this;
    }

    /**
     * 클리어 데이터의 최대 SongRank 범위를 설정합니다.
     *
     * @param maxSongRank 최대 SongRank 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMaxSongRank(int maxSongRank) {
        checkSongRank(maxSongRank);
        this.maxSongRank = maxSongRank;
        return this;
    }

    private void checkSongRank(int songRank) {
        checkInt(songRank, "SongRank");
    }

    /**
     * 클리어 데이터의 TotalRank를 설정합니다.
     *
     * @param totalRank 설정할 클리어 데이터의 TotalRank입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setTotalRank(int totalRank) {
        checkTotalRank(totalRank);
        this.minTotalRank = totalRank;
        this.maxTotalRank = totalRank;
        return this;
    }

    /**
     * 클리어 데이터의 TotalRank 범위를 설정합니다.
     *
     * @param minTotalRank 최소 TotalRank 값입니다.
     * @param maxTotalRank 최대 TotalRank 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public ClearQuery setTotalRank(int minTotalRank, int maxTotalRank) {
        checkTotalRank(minTotalRank);
        checkTotalRank(maxTotalRank);
        if(minTotalRank > maxTotalRank)
            throw new IllegalArgumentException("maxTotalRank cannot be less than the minTotalRank");
        this.minTotalRank = minTotalRank;
        this.maxTotalRank = maxTotalRank;
        return this;
    }

    /**
     * 클리어 데이터의 최소 TotalRank 범위를 설정합니다.
     *
     * @param minTotalRank 최소 TotalRank 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMinTotalRank(int minTotalRank) {
        checkTotalRank(minTotalRank);
        this.minTotalRank = minTotalRank;
        return this;
    }

    /**
     * 클리어 데이터의 최대 TotalRank 범위를 설정합니다.
     *
     * @param maxTotalRank 최대 TotalRank 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public ClearQuery setMaxTotalRank(int maxTotalRank) {
        checkTotalRank(maxTotalRank);
        this.maxTotalRank = maxTotalRank;
        return this;
    }

    private void checkTotalRank(int totalRank) {
        checkInt(totalRank, "TotalRank");
    }

    private void checkString(String value) {
        if(value == null) throw new NullPointerException();
    }

    private void checkInt(int value, String name) {
        if(value < 0) throw new IllegalArgumentException(name + " cannot be less than 0");
    }

    /**
     * 클리어 데이터를 필터링하여 설정된 조건에 맞는 데이터를 반환합니다.
     *
     * @param clearData 클리어 데이터 배열입니다.
     * @return 조건에 맞는 클리어 데이터의 목록입니다.
     */
    public List<ClearData> checkClear(ClearData... clearData) {
        return Arrays.stream(clearData).filter(clear -> (name == null || clear.getName().contains(name))
            && (userCode == -1 || clear.getUserCode() == userCode)
            && (mapId == -1 || clear.getMapId() == mapId)
            && (minSpeed == -1 || clear.getSpeed() >= minSpeed)
            && (maxSpeed == -1 || clear.getSpeed() <= maxSpeed)
            && (minXAccuracy == -1 || clear.getXAccuracy() >= minXAccuracy)
            && (maxXAccuracy == -1 || clear.getXAccuracy() <= maxXAccuracy)
            && (minPlayPoint == -1 || clear.getPlayPoint() >= minPlayPoint)
            && (maxPlayPoint == -1 || clear.getPlayPoint() <= maxPlayPoint)
            && (minLocalRank == -1 || clear.getLocalRank() >= minLocalRank)
            && (maxLocalRank == -1 || clear.getLocalRank() <= maxLocalRank)
            && (minSongRank == -1 || clear.getSongRank() >= minLocalRank)
            && (maxSongRank == -1 || clear.getSongRank() <= maxSongRank)
            && (minTotalRank == -1 || clear.getTotalRank() >= minTotalRank)
            && (maxTotalRank == -1 || clear.getTotalRank() <= maxTotalRank)).toList();
    }
}
