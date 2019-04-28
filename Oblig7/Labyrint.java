import java.util.Scanner;
import java.io.*;

public class Labyrint {
    public Rute[][] ruteArr;
    Lenkeliste<String> liste;
    int lengde;
    int hoyde;

    public Rute hentRute(int x, int y) {
        return ruteArr[x][y];
    }

    public Rute hentRute(int[] koordinater) {
        return ruteArr[koordinater[0]][koordinater[1]];
    }

    private Labyrint(int lengde, int hoeyde) {
        this.lengde = lengde;
        this.hoyde = hoeyde;
    }

    private void settLabyrint(Rute[][] ruteArr) {
         this.ruteArr = ruteArr;
    }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner lesFil = new Scanner(fil);
        int teller = 0;
        String linje = lesFil.nextLine();

        String[] split = linje.split(" ");
        int x, y;
        y = Integer.parseInt(split[0]);
        x = Integer.parseInt(split[1]);
        Rute[][] ruteArr = new Rute[x][y];

        Labyrint labyrint = new Labyrint(x, y);

        while (lesFil.hasNextLine()) {
            linje = lesFil.nextLine();
            for (int i = 0; i < linje.length(); i++) {
                //i = kolonne, teller = rad
                if (String.valueOf(linje.charAt(i)).equals(".")) {
                    if (i == 0 || teller == 0 || i == x - 1 || teller == y - 1) {
                        Aapning aapning = new Aapning(labyrint, i, teller);
                        ruteArr[i][teller] = aapning;
                    } else {
                        HvitRute hvitRute = new HvitRute(labyrint, i, teller);
                        ruteArr[i][teller] = hvitRute;
                    }
                } else {
                    SortRute sortRute = new SortRute(labyrint, i, teller);
                    ruteArr[i][teller] = sortRute;
                }
            }
            teller++;
        }
        labyrint.settLabyrint(ruteArr);
        return labyrint;
    }

    @Override
    public String toString() {
        String s = "";

        for (int y = 0; y < hoyde; y++) {
            for (int x = 0; x < lengde; x++) {
                s += Character.toString(ruteArr[x][y].tilTegn());
            }
            s += "\n";
        }
        return s;
    }

    public Lenkeliste<String> finnUtveiFra(int kol, int rad) {
        liste = new Lenkeliste<String>();
        //hvis vi vil at koordinater starter fra 1 og ikke fra 0
        //kol -= 1;
        //rad -= 1;
        hentRute(kol, rad).finnUtvei();
        return liste;
    }
}
