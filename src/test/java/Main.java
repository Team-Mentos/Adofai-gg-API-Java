import xyz.krmentos.adofaigg.api.AdofaiGG;

public class Main {
    public static void main(String[] args) {
        AdofaiGG adofaiGG = new AdofaiGG();
        System.out.println(adofaiGG.getMapById(1).getSong());
    }
}
