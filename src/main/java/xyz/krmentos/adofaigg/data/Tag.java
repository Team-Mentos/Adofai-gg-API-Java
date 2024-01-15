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

/**
 * Adofai.gg API에서 사용되는 맵 태그를 정의합니다.
 *
 * <p>{@link Tag} 열거형의 각 상수는 특정 맵에 대한 태그를 나타냅니다.
 *
 * <p>{@link #convertTag(String)} 메서드를 사용하여 문자열을 {@link Tag} 열거형으로 변환할 수 있습니다.
 *
 * <p>태그는 맵당 5개까지 사용할 수 있고 5개를 초과할 시 아래 규칙에 따라 제외됩니다.
 * <ul>
 *     <li>{@link #Memorization} 태그가 포함되어 있을 경우 {@link #Gimmick} 태그를 제외합니다.</li>
 *     <li>{@link #FunkyBeat} 태그가 포함되어 있을 경우 {@link #Tresillo} {@link #Triplet} {@link #Quintuplet} {@link #Septuplet} 태그 순서로 제외합니다.</li>
 *     <li>그 외의 경우 {@link #Tresillo} {@link #Swing} {@link #Triplet} {@link #MagicShape} {@link #Gallop} 태그 순서로 제외합니다.</li>
 * </ul>
 *
 * @author Jongyeol
 * @see MapData
 */
public enum Tag {
    /**
     * <b>동시치기</b>
     * <p>2개 키를 동시에 입력해야 하는 기믹성 타일이 포함되거나 트릴을 제외한 15도, 30도 간격을 둔 두 타일이 포함된 맵에 사용되는 태그입니다.
     * <p>{@link #Pseudo2} 태그가 존재할 경우 제외됩니다.
     */
    Pseudo,
    /**
     * <b>2+동타</b>
     * <p>3개 이상의 키를 동시에 입력해야 하는 기믹성 타일이 포함된 맵에 사용되는 태그입니다.
     */
    Pseudo2,
    /**
     * <b>셋잇단</b>
     * <p>하나의 박자를 3배수 등분 한 박자가 여러번 포함된 맵에 사용되는 태그입니다.
     * <p>태그가 5개를 초과할 경우 제외될 수 있습니다.
     */
    Triplet,
    /**
     * <b>다섯잇단</b>
     * <p>하나의 박자를 5배수 등분 한 박자가 포함된 맵에 사용되는 태그입니다.
     * <p>태그가 5개를 초과하고 {@link #FunkyBeat}태그가 존재할 경우 제외될 수 있습니다.
     */
    Quintuplet,
    /**
     * <b>일곱잇단</b>
     * <p>하나의 박자를 7배수 등분 한 박자가 포함된 맵에 사용되는 태그입니다.
     * <p>태그가 5개를 초과하고 {@link #FunkyBeat}태그가 존재할 경우 제외될 수 있습니다.
     */
    Septuplet,
    /**
     * <b>폴리리듬</b>
     * <p>2개 이상의 대조되는 리듬이 합쳐진 리듬이 여러번 포함된 맵에 사용되는 태그입니다.
     * <p>겹박자일 경우 제외될 수 있습니다.
     */
    PolyRhythm,
    /**
     * <b>스윙</b>
     * <p>스트레이트 비트에서 벗어나 셔플(2/3박 - 1/3박 등)의 성향을 띠는 박자가 20% 내외로 존재하는 맵에 사용되는 태그입니다.
     * <p>태그가 5개를 초과할 경우 제외될 수 있습니다.
     */
    Swing,
    /**
     * <b>트레실로</b>
     * <p>두개의 박자를 3-3-2로 나눈 박자가 여러번 포함된 맵에 사용되는 태그입니다.
     * <p>태그가 5개를 초과할 경우 제외될 수 있습니다.
     */
    Tresillo,
    /**
     * <b>개박</b>
     * <p>일반적인 박자 구성이 아닌 불규칙적인 박자의 타일이 포함된 맵에 사용되는 태그입니다.
     */
    FunkyBeat,
    /**
     * <b>64+비트</b>
     * <p>64비트 이상의 비트(64분음표 이상)가 포함된 맵에 사용되는 태그입니다.
     */
    Beat64,
    /**
     * <b>변속</b>
     * <p>기본 BPM의 정수배가 아닌 BPM이 포함된 맵에 사용되는 태그입니다.
     * <p>기본 BPM이 변화하는 경우 포함됩니다.
     */
    Acceleration,
    /**
     * <b>질주</b>
     * <p>600BPM(보편적인 16비트)이상의 일직선 기반 연타가 곡의 한 파트 이상 포함되어있거나
     * 1200BPM(보편적인 32비트)이상의 일직선 연타가 2초 이상 포함되어 있는 맵에 사용되는 태그입니다.
     * <p>태그가 5개를 초과할 경우 제외될 수 있습니다.
     */
    Gallop,
    /**
     * <b>마법진</b>
     * <p>등속 연타나 같은 구간이 반복되는 파트에서 의도적으로 트랙을 꼬아 만든 기믹이 포함된 맵에 사용되는 태그입니다.
     * <p>태그가 5개를 초과할 경우 제외될 수 있습니다.
     */
    MagicShape,
    /**
     * <b>암기</b>
     * <p>맵의 일부분을 보여준 다음 타일을 가린 후 지나가는 기믹이 포함된 맵에 사용되는 태그입니다.
     */
    Memorization,
    /**
     * <b>DLC</b>
     * <p>DLC 기능이 사용된 맵에 사용되는 태그입니다.
     */
    DLC,
    /**
     * <b>4분이상</b>
     * <p>노래의 길이가 4분 이상인 맵에 사용되는 태그입니다.
     */
    Long,
    /**
     * <b>흰토끼</b>
     * <p>최고 BPM이 300 미만인 맵에 사용되는 태그입니다.
     */
    Slow,
    /**
     * <b>배속변경X</b>
     * <p>토끼와 달팽이가 존재하지 않는 맵에 사용되는 태그입니다.
     */
    NoSpeedChange,
    /**
     * <b>소용돌이X</b>
     * <p>소용돌이가 존재하지 않는 맵에 사용되는 태그입니다.
     */
    NoTwirl,
    /**
     * <b>기믹</b>
     * <p>통상적인 얼불춤의 형식이 아닌 독특한 플레이 시스템이 포함된 맵에 사용되는 태그입니다.
     * <p>태그가 5개를 초과하고 {@link #Memorization}태그가 존재할 경우 제외될 수 있습니다.
     */
    Gimmick,
    /**
     * <b>NSFW</b>
     * <p>선정적이거나 잔인한 컨텐츠가 포함된 맵에 사용되는 태그입니다.
     */
    NSFW,
    /**
     * <b>급가속</b>
     * <p>16배 이상의 토끼가 하나 이상 존재하는 맵에 사용되는 태그입니다.
     */
    SuddenAcceleration;

    /**
     * 주어진 문자열을 Tag 열거형으로 변환합니다.
     *
     * @param name 변환할 문자열입니다.
     * @return 주어진 문자열에 해당하는 Tag 상수입니다.
     * @throws NullPointerException 주어진 문자열이 null인 경우 예외가 발생합니다.
     * @throws IllegalArgumentException 주어진 문자열이 어떤 Tag 상수와도 일치하지 않는 경우 예외가 발생합니다.
     */
    public static Tag convertTag(String name) {
        if(name == null) throw new NullPointerException();
        return switch(name) {
            case "#동시치기" -> Pseudo;
            case "#2+동타" -> Pseudo2;
            case "#셋잇단" -> Triplet;
            case "#다섯잇단" -> Quintuplet;
            case "#일곱잇단" -> Septuplet;
            case "#폴리리듬" -> PolyRhythm;
            case "#스윙" -> Swing;
            case "#트레실로" -> Tresillo;
            case "#개박" -> FunkyBeat;
            case "#64+비트" -> Beat64;
            case "#변속" -> Acceleration;
            case "#질주" -> Gallop;
            case "#마법진" -> MagicShape;
            case "#암기" -> Memorization;
            case "#DLC" -> DLC;
            case "#4분이상" -> Long;
            case "#흰토끼" -> Slow;
            case "#배속변경X" -> NoSpeedChange;
            case "#소용돌이X" -> NoTwirl;
            case "#기믹" -> Gimmick;
            case "#NSFW" -> NSFW;
            case "#급가속" -> SuddenAcceleration;
            default -> throw new IllegalArgumentException(name);
        };
    }
}
