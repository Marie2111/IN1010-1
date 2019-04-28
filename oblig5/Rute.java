import java.util.Iterator;
import java.util.Arrays;

abstract public class Rute {
    Labyrint labyrint;
    int[] nord = new int[2];
    int[] sor = new int[2];
    int[] ost = new int[2];
    int[] vest = new int[2];
    public int x;
    public int y;

    public Rute(Labyrint labyrint, int x, int y) {
        this.labyrint = labyrint;
        this.x = x;
        this.y = y;
        nord[0] = x;
        nord[1] = y - 1;
        sor[0] = x;
        sor[1] = y + 1;
        ost[0] = x + 1;
        ost[1] = y;
        vest[0] = x - 1;
        vest[1] = y;
    }

    abstract char tilTegn();

    public void gaa(int[] komFra, String utvei) {
        //hvis koordinater starter fra 1 og ikke fra 0: x+1, y+1
        utvei += "("+ x + ", "+ y +" ) --> ";
        if(!Arrays.equals(komFra, nord)) {
            sjekkNabo(nord, utvei);
        }

        if(!Arrays.equals(komFra, sor)) {
            sjekkNabo(sor, utvei);
        }

        if(!Arrays.equals(komFra, ost)) {
            sjekkNabo(ost, utvei);
        }

        if(!Arrays.equals(komFra, vest)) {
            sjekkNabo(vest, utvei);
        }
    }

    public int[] minPosisjon() {
        int[] minPosisjon = new int[2];
        minPosisjon[0] = x;
        minPosisjon[1] = y;
        return minPosisjon;
    }

    private void sjekkNabo(int[] naboKoordinater, String utvei) {
        Rute rute = labyrint.hentRute(naboKoordinater);

        //if(rute instanceof HvitRute) {
        rute.gaa(minPosisjon(), utvei);
    }

    public void finnUtvei() {
        String utvei = "";
        gaa(minPosisjon(), utvei);
    }

}
