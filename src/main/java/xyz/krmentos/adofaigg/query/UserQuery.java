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
import xyz.krmentos.adofaigg.data.UserData;

import java.util.Arrays;
import java.util.List;

/**
 * 이 클래스는 Adofai.gg API에서 사용되는 유저 데이터를 필터링하기 위한 클래스입니다.
 *
 * <p>{@link UserQuery}를 사용하여 맵 데이터를 특정 조건에 맞게 필터링할 수 있습니다.
 *
 * <p>다양한 메서드를 통해 각 필터 조건을 설정하고, {@link #checkUser(UserData...)} 메서드를 호출하여 필터링된 클리어 데이터를 얻을 수 있습니다.
 *
 * @author Jongyeol
 * @see UserData
 */
public class UserQuery {
    private String userName;
    private int minRank = -1;
    private int maxRank = -1;
    private double minTotalPP = -1;
    private double maxTotalPP = -1;

    /**
     * 유저의 이름을 설정합니다.
     *
     * @param userName 설정할 유저의 이름입니다.
     * @return {@link UserQuery} 개체 자신을 반환합니다.
     * @throws NullPointerException 값이 null일 경우 예외가 발생합니다.
     */
    public UserQuery setUserName(String userName) {
        checkString(userName);
        this.userName = userName;
        return this;
    }

    /**
     * 유저 데이터의 랭크를 설정합니다.
     *
     * @param rank 설정할 유저 데이터의 랭크입니다.
     * @return {@link UserQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public UserQuery setRank(int rank) {
        checkRank(rank);
        minRank = rank;
        maxRank = rank;
        return this;
    }

    /**
     * 유저 데이터의 랭크 범위를 설정합니다.
     *
     * @param minRank 최소 랭크 값입니다.
     * @param maxRank 최대 랭크 값입니다.
     * @return {@link UserQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public UserQuery setRank(int minRank, int maxRank) {
        checkRank(minRank);
        checkRank(maxRank);
        if(minRank > maxRank) throw new IllegalArgumentException("maxRank cannot be less than the minRank");
        this.minRank = minRank;
        this.maxRank = maxRank;
        return this;
    }

    /**
     * 유저 데이터의 최소 랭크 범위를 설정합니다.
     *
     * @param minRank 최소 랭크 값입니다.
     * @return {@link UserQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public UserQuery setMinRank(int minRank) {
        checkRank(minRank);
        this.minRank = minRank;
        return this;
    }

    /**
     * 유저 데이터의 최대 랭크 범위를 설정합니다.
     *
     * @param maxRank 최대 랭크 값입니다.
     * @return {@link UserQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public UserQuery setMaxRank(int maxRank) {
        checkRank(maxRank);
        this.maxRank = maxRank;
        return this;
    }

    private void checkRank(int rank) {
        if(rank < 0) throw new IllegalArgumentException("rank cannot be less than 0");
    }

    /**
     * 유저 데이터의 PP를 설정합니다.
     *
     * @param totalPP 설정할 유저 데이터의 PP입니다.
     * @return {@link UserQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public UserQuery setTotalPP(double totalPP) {
        checkTotalPP(totalPP);
        minTotalPP = totalPP;
        maxTotalPP = totalPP;
        return this;
    }

    /**
     * 유저 데이터의 PP 범위를 설정합니다.
     *
     * @param minTotalPP 최소 PP 값입니다.
     * @param maxTotalPP 최대 PP 값입니다.
     * @return {@link UserQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작거나 최소값이 최대값보다 큰 경우 발생합니다.
     */
    public UserQuery setTotalPP(double minTotalPP, double maxTotalPP) {
        checkTotalPP(minTotalPP);
        checkTotalPP(maxTotalPP);
        if(minTotalPP > maxTotalPP) throw new IllegalArgumentException("maxTotalPP cannot be less than the minTotalPP");
        this.minTotalPP = minTotalPP;
        this.maxTotalPP = maxTotalPP;
        return this;
    }

    /**
     * 유저 데이터의 최소 PP 범위를 설정합니다.
     *
     * @param minTotalPP 최소 PP 값입니다.
     * @return {@link UserQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public UserQuery setMinTotalPP(double minTotalPP) {
        checkTotalPP(minTotalPP);
        this.minTotalPP = minTotalPP;
        return this;
    }

    /**
     * 유저 데이터의 최대 PP 범위를 설정합니다.
     *
     * @param maxTotalPP 최대 PP 값입니다.
     * @return {@link UserQuery} 개체 자신을 반환합니다.
     * @throws IllegalArgumentException 값이 0보다 작을 경우 예외가 발생합니다.
     */
    public UserQuery setMaxTotalPP(double maxTotalPP) {
        checkTotalPP(maxTotalPP);
        this.maxTotalPP = maxTotalPP;
        return this;
    }

    private void checkTotalPP(double totalPP) {
        if(totalPP < 0) throw new IllegalArgumentException("TotalPP cannot be less than 0");
    }

    private void checkString(String value) {
        if(value == null) throw new NullPointerException();
    }

    /**
     * 유저 데이터를 필터링하여 설정된 조건에 맞는 데이터를 반환합니다.
     *
     * @param userData 유저 데이터 배열입니다.
     * @return 조건에 맞는 클리어 데이터의 목록입니다.
     */
    public List<UserData> checkUser(UserData... userData) {
        return Arrays.stream(userData).filter(user -> (userName == null || user.getUserName().contains(userName))
            && (minRank == -1 || user.getRank() >= minRank)
            && (maxRank == -1 || user.getRank() <= maxRank)
            && (minTotalPP == -1 || user.getTotalPP() >= minTotalPP)
            && (maxTotalPP == -1 || user.getTotalPP() <= maxTotalPP)).toList();
    }
}
