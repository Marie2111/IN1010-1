public class Aapning extends HvitRute {

    public Aapning(Labyrint labyrint, int x, int y) {
        super(labyrint, x, y);
    }


    public void gaa(int[] komFra, String utvei) {
        utvei += "(" + x + ", " + y + ") MÅL!";
        labyrint.liste.leggTil(utvei);
    }
}
