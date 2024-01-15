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

package xyz.krmentos.adofaigg.query;

import xyz.krmentos.adofaigg.data.MapData;
import xyz.krmentos.adofaigg.data.Tag;

import java.util.Arrays;
import java.util.List;

/**
 * 이 클래스는 Adofai.gg API에서 사용되는 맵 데이터를 필터링하기 위한 클래스입니다.
 *
 * <p>{@link MapQuery}를 사용하여 맵 데이터를 특정 조건에 맞게 필터링할 수 있습니다.
 *
 * <p>다양한 메서드를 통해 각 필터 조건을 설정하고, {@link #checkMap(MapData...)} 메서드를 호출하여 필터링된 클리어 데이터를 얻을 수 있습니다.
 *
 * @author Jongyeol
 * @see MapData
 */
public class MapQuery {
    private String song;
    private String artist;
    private float minDifficulty = -999;
    private float maxDifficulty = -999;
    private String creator;
    private byte ew = -1;
    private double minBPM = -1;
    private double maxBPM = -1;
    private int minTiles = -1;
    private int maxTiles = -1;
    private boolean tagAllNeed;
    private Tag[] tags;
    private byte dlc = -1;

    /**
     * 맵의 제목을 설정합니다.
     *
     * @param song 설정할 맵의 제목입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws NullPointerException 값이 null일 경우 예외가 발생합니다.
     */
    public MapQuery setSong(String song) {
        checkString(song);
        this.song = song;
        return this;
    }

    /**
     * 맵의 아티스트를 설정합니다.
     *
     * @param artist 설정할 맵의 아티스트입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws NullPointerException 값이 null일 경우 예외가 발생합니다.
     */
    public MapQuery setArtist(String artist) {
        checkString(artist);
        this.artist = artist;
        return this;
    }

    /**
     * 맵 데이터의 난이도를 설정합니다.
     *
     * @param difficulty 설정할 맵 데이터의 난이도입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작고 존재하지 않는 난이도일 경우 예외가 발생합니다.
     */
    public MapQuery setDifficulty(float difficulty) {
        checkDifficulty(difficulty);
        minDifficulty = difficulty;
        maxDifficulty = difficulty;
        return this;
    }

    /**
     * 맵 데이터의 난이도 범위를 설정합니다.
     *
     * @param minDifficulty 최소 난이도 값입니다.
     * @param maxDifficulty 최대 난이도 값입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작고 존재하지 않는 난이도거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public MapQuery setDifficulty(float minDifficulty, float maxDifficulty) {
        checkDifficulty(minDifficulty);
        checkDifficulty(maxDifficulty);
        if(minDifficulty > maxDifficulty)
            throw new IllegalArgumentException("maxDifficulty cannot be less than the minDifficulty");
        this.minDifficulty = minDifficulty;
        this.maxDifficulty = maxDifficulty;
        return this;
    }

    /**
     * 맵 데이터의 최소 난이도 범위를 설정합니다.
     *
     * @param minDifficulty 최소 난이도 값입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작고 존재하지 않는 난이도일 경우 예외가 발생합니다.
     */
    public MapQuery setMinDifficulty(float minDifficulty) {
        checkDifficulty(minDifficulty);
        this.minDifficulty = minDifficulty;
        return this;
    }

    /**
     * 맵 데이터의 최대 난이도 범위를 설정합니다.
     *
     * @param maxDifficulty 최대 난이도 값입니다.
     * @return {@link ClearQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작고 존재하지 않는 난이도일 경우 예외가 발생합니다.
     */
    public MapQuery setMaxDifficulty(float maxDifficulty) {
        checkDifficulty(maxDifficulty);
        this.maxDifficulty = maxDifficulty;
        return this;
    }

    private void checkDifficulty(float difficulty) {
        if((difficulty < 0 && difficulty != -1 && difficulty != -2 && difficulty != -10) || difficulty > 21)
            throw new IllegalArgumentException("difficulty cannot be " + difficulty);
    }

    /**
     * 맵의 제작자를 설정합니다.
     *
     * @param creator 설정할 맵의 제작자입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws NullPointerException 값이 null일 경우 예외가 발생합니다.
     */
    public MapQuery setCreator(String creator) {
        checkString(creator);
        this.creator = creator;
        return this;
    }

    /**
     * 맵의 광과민성 발작주의를 설정합니다.
     *
     * @param ew 설정할 맵의 광과민성 발작주의 여부입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     */
    public MapQuery setEW(boolean ew) {
        this.ew = (byte) (ew ? 1 : 0);
        return this;
    }

    /**
     * 맵 데이터의 BPM를 설정합니다.
     *
     * @param bpm 설정할 맵 데이터의 BPM입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public MapQuery setBPM(double bpm) {
        checkBPM(bpm);
        minBPM = bpm;
        maxBPM = bpm;
        return this;
    }

    /**
     * 맵 데이터의 BPM 범위를 설정합니다.
     *
     * @param minBPM 최소 BPM 값입니다.
     * @param maxBPM 최대 BPM 값입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public MapQuery setBPM(double minBPM, double maxBPM) {
        checkBPM(minBPM);
        checkBPM(maxBPM);
        if(minBPM > maxBPM) throw new IllegalArgumentException("maxBPM cannot be less than the minBPM");
        this.minBPM = minBPM;
        this.maxBPM = maxBPM;
        return this;
    }

    /**
     * 맵 데이터의 최소 BPM 범위를 설정합니다.
     *
     * @param minBPM 최소 BPM 값입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public MapQuery setMinBPM(double minBPM) {
        checkBPM(minBPM);
        this.minBPM = minBPM;
        return this;
    }

    /**
     * 맵 데이터의 최대 BPM 범위를 설정합니다.
     *
     * @param maxBPM 최대 BPM 값입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public MapQuery setMaxBPM(double maxBPM) {
        checkBPM(maxBPM);
        this.maxBPM = maxBPM;
        return this;
    }

    private void checkBPM(double bpm) {
        if(bpm < 0) throw new IllegalArgumentException("bpm cannot be less than 0");
    }

    /**
     * 맵 데이터의 타일수를 설정합니다.
     *
     * @param tiles 설정할 맵 데이터의 타일수입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public MapQuery setTiles(int tiles) {
        checkTiles(tiles);
        this.minTiles = tiles;
        this.maxTiles = tiles;
        return this;
    }

    /**
     * 맵 데이터의 타일수 범위를 설정합니다.
     *
     * @param minTiles 최소 타일수 값입니다.
     * @param maxTiles 최대 타일수 값입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public MapQuery setTiles(int minTiles, int maxTiles) {
        checkTiles(minTiles);
        checkTiles(maxTiles);
        if(minTiles > maxTiles) throw new IllegalArgumentException("maxTiles cannot be less than the minTiles");
        this.minTiles = minTiles;
        this.maxTiles = maxTiles;
        return this;
    }

    /**
     * 맵 데이터의 최소 타일수 범위를 설정합니다.
     *
     * @param minTiles 최소 타일수 값입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public MapQuery setMinTiles(int minTiles) {
        checkTiles(minTiles);
        this.minTiles = minTiles;
        return this;
    }

    /**
     * 맵 데이터의 최대 타일수 범위를 설정합니다.
     *
     * @param maxTiles 최대 타일수 값입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public MapQuery setMaxTiles(int maxTiles) {
        checkTiles(maxTiles);
        this.maxTiles = maxTiles;
        return this;
    }

    private void checkTiles(int tiles) {
        if(tiles < 0) throw new IllegalArgumentException("tiles cannot be less than 0");
    }

    /**
     * 맵 데이터의 태그를 설정합니다.
     *
     * @param tagAllNeed 모든 태그가 필요한지 여부를 설정합니다.
     * @param tags 설정할 맵의 태그입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 모든 태그가 필요할 경우 태그가 5개가 넘을 경우 예외가 발생합니다.
     * @throws NullPointerException 태그가 null일 경우 예외가 발생합니다.
     */
    public MapQuery setTags(boolean tagAllNeed, Tag... tags) {
        if(tagAllNeed && tags.length > 5) throw new IllegalArgumentException("tag cannot be more than 5");
        for(Tag tag : tags) if(tag == null) throw new NullPointerException();
        this.tagAllNeed = tagAllNeed;
        this.tags = tags;
        return this;
    }

    /**
     * 맵의 DLC 여부를 설정합니다.
     *
     * @param dlc 설정할 맵의 DLC 여부입니다.
     * @return {@link MapQuery} 개체 자신을 반환합니다.
     */
    public MapQuery setDlc(boolean dlc) {
        this.dlc = (byte) (dlc ? 1 : 0);
        return this;
    }

    private void checkString(String value) {
        if(value == null) throw new NullPointerException();
    }

    /**
     * 맵 데이터를 필터링하여 설정된 조건에 맞는 데이터를 반환합니다.
     *
     * @param mapData 맵 데이터 배열입니다.
     * @return 조건에 맞는 클리어 데이터의 목록입니다.
     */
    public List<MapData> checkMap(MapData... mapData) {
        return Arrays.stream(mapData).filter(map -> (song == null || map.getSong().contains(song))
            && (artist == null || map.getArtist().contains(artist))
            && (minDifficulty == -999 || map.getDifficulty() >= minDifficulty)
            && (maxDifficulty == -999 || map.getDifficulty() <= maxDifficulty)
            && (creator == null || map.getCreator().contains(creator))
            && (ew == -1 || map.isEw() == (ew == 1))
            && (minBPM == -1 || map.getBpm() >= minBPM)
            && (maxBPM == -1 || map.getBpm() <= maxBPM)
            && (minTiles == -1 || map.getTiles() >= minTiles)
            && (maxTiles == -1 || map.getTiles() <= maxTiles)
            && (tags == null || (tagAllNeed ?
            (Arrays.stream(map.getTags()).allMatch(tag -> Arrays.stream(tags).anyMatch(tag1 -> tag == tag1)))
            : (Arrays.stream(map.getTags()).anyMatch(tag -> Arrays.stream(tags).anyMatch(tag1 -> tag == tag1)))))
            && (dlc == -1 || map.isDlc() == (dlc == 1))).toList();
    }
}
