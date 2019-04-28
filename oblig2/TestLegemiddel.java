//import java.util.ArrayList;

public class TestLegemiddel{
    private static int teller = 1;

    public static void main(String [] args){

        LegemiddelA legemiddelA = new LegemiddelA("Imigran", 150.6, 1.2, 3);
        LegemiddelB legemiddelB = new LegemiddelB("Paracet", 50.2, 0.5, 1);
        LegemiddelC legemiddelC = new LegemiddelC("Otrivin", 69.9, 0.2);

        System.out.println("Tester legemiddelsnavn");
        System.out.println("Legemiddel A:");
        testNavn(legemiddelA.hentNavn(), "Imigran");
        System.out.println("Legemiddel B:");
        testNavn(legemiddelB.hentNavn(), "Paracet");
        System.out.println("Legemiddel C:");
        testNavn(legemiddelC.hentNavn(), "Otrivin");
        System.out.println();

        System.out.println("Tester pris");
        System.out.println("Legemiddel A:");
        testPris(legemiddelA.hentPris(), 150.6);
        System.out.println("Legemiddel B:");
        testPris(legemiddelB.hentPris(), 50.2);
        System.out.println("Legemiddel C:");
        testPris(legemiddelC.hentPris(), 69.9);
        System.out.println();

        System.out.println("Tester virkestoff");
        System.out.println("Legemiddel A:");
        testVirkestoff(legemiddelA.hentVirkestoff(),1.2);
        System.out.println("Legemiddel B:");
        testVirkestoff(legemiddelB.hentVirkestoff(),0.5);
        System.out.println("Legemiddel C:");
        testVirkestoff(legemiddelC.hentVirkestoff(),0.2);
        System.out.println();

        System.out.println("Tester styrke");
        System.out.println("Legemiddel A:");
        testStyrke(legemiddelA.hentNarkotiskStyrke(), 3);
        System.out.println("Legemiddel B:");
        testStyrke(legemiddelB.hentVanedannedeStyrke(), 1);
        System.out.println();

        System.out.println("Tester ID");
        System.out.println("Legemiddel A:");
        testID(legemiddelA.hentId(), 0);
        System.out.println("Legemiddel B:");
        testID(legemiddelB.hentId(), 1);
        System.out.println("Legemiddel C:");
        testID(legemiddelC.hentId(), 2);
        System.out.println();

        legemiddelA.settNyPris(200.1);
        legemiddelB.settNyPris(85);
        legemiddelC.settNyPris(79.4);

        System.out.println("Tester en ny pris");
        System.out.println("Legemiddel A:");
        testPris(legemiddelA.hentPris(), 200.1);
        System.out.println("Legemiddel B:");
        testPris(legemiddelB.hentPris(), 85);
        System.out.println("Legemiddel C:");
        testPris(legemiddelC.hentPris(), 79.4);
        System.out.println();

        /* ArrayList<Legemiddel> legemidler = new ArrayList<Legemiddel>();
        legemidler.add(new LegemiddelA("Imigran", 150.6, 1.2, 3));
        legemidler.add(new LegemiddelB("Paracet", 50.2, 0.5, 1));
        legemidler.add(new LegemiddelC("Otrivin", 69.9, 0.2));

        for(Legemiddel legemiddel: legemidler){
            System.out.println("Type: " + legemiddel.getClass());
            System.out.println("Navn: " + legemiddel.hentNavn());
            System.out.println("ID:  " + legemiddel.hentId());
            System.out.println("Pris: " + legemiddel.hentPris());
            System.out.println("Virkestoff: " + legemiddel.hentVirkestoff());

            if(legemiddel instanceof LegemiddelA){
                LegemiddelA legemiddelA = (LegemiddelA) legemiddel;
                System.out.println("Narkotiskstyrke: " + legemiddelA.hentNarkotiskStyrke());
                System.out.println();
            } else if (legemiddel instanceof LegemiddelB){
                LegemiddelB legemiddelB=(LegemiddelB) legemiddel;
                System.out.println("Vanedannedestyrke: " + legemiddelB.hentVanedannedeStyrke());
                System.out.println();
            } else if (legemiddel instanceof LegemiddelC){
                LegemiddelC legemiddelC = (LegemiddelC) legemiddel;
                System.out.println("En ny pris er: " + legemiddelC.settNyPris(81.2));
                System.out.println();
            }
        }*/
    }

    public static boolean testPris(double faktiskResultat, double forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    public static boolean testNavn(String faktiskResultat, String forventetResultat) {
        if (faktiskResultat.equalsIgnoreCase(forventetResultat)) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat.equalsIgnoreCase(forventetResultat);
    }

    public static boolean testID(int faktiskResultat, int forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    public static boolean testVirkestoff(double faktiskResultat, double forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }

    public static boolean testStyrke(int faktiskResultat, int forventetResultat) {
        if (faktiskResultat == forventetResultat) {
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }
}
