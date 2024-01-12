package xyz.krmentos.adofaigg.data;

/**
 * Adofai.gg API에서 사용되는 맵 태그를 정의합니다.
 *
 * <p>{@link Tag} 열거형의 각 상수는 특정 맵에 대한 태그를 나타냅니다.
 *
 * <p>{@link #convertTag(String)} 메서드를 사용하여 문자열을 {@link Tag} 열거형으로 변환할 수 있습니다.
 *
 * @author Jongyeol
 * @see MapData
 */
public enum Tag {
    Pseudo,
    Pseudo2,
    Triplet,
    Quintuplet,
    Septuplet,
    PolyRhythm,
    Swing,
    Tresillo,
    FunkyBeat,
    Beat64,
    Acceleration,
    Gallop,
    MagicShape,
    Memorization,
    DLC,
    Long,
    Slow,
    NoSpeedChange,
    NoTwirl,
    Gimmick,
    NSFW,
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
