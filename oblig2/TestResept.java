//import java.util.ArrayList;

public class TestResept{
    private static int teller = 1;

    public static void main(String [] args){

        PResept pResept = new PResept(new LegemiddelA("Imigran", 150, 1.2, 3), new Lege("Dr.Olavson"), 1, 1);
        MilitaerResept mResept = new MilitaerResept(new LegemiddelB("Paracet", 180, 2, 3), new Lege("Dr.Grodas"), 2, 1);
        BlaaResept bResept = new BlaaResept(new LegemiddelC("Sedix", 250, 2), new Lege("Dr.Brunne"), 3, 2);

        System.out.println("Tester farge");
        System.out.println("P-resept:");
        testFarge(pResept.farge(), "hvit");
        System.out.println("Militaer resept:");
        testFarge(mResept.farge(), "hvit");
        System.out.println("Blaa resept:");
        testFarge(bResept.farge(), "blaa");
        System.out.println();

        System.out.println("Tester pris aa betale");
        System.out.println("P-resept:");
        testPrisAaBetale(pResept.prisAaBetale(), 34);
        System.out.println("Militaer resept:");
        testPrisAaBetale(mResept.prisAaBetale(), 0);
        System.out.println("Blaa resept:");
        testPrisAaBetale(bResept.prisAaBetale(), 62.5);
        System.out.println();

        /* ArrayList<Resept> resepter = new ArrayList<Resept>();
        resepter.add(new PResept(new LegemiddelA("Imigran", 150, 1.2, 3), new Lege("Dr.Olavson"), 1, 1));
        resepter.add(new MilitaerResept(new LegemiddelB("Paracet", 180, 2, 3), new Lege("Dr.Grodas"), 2, 1));
        resepter.add(new BlaaResept(new LegemiddelC("Sedix", 250, 2), new Lege("Dr.Brunne"), 3, 2));

        for(Resept resept: resepter){
            System.out.println("Skriver ut all data");
            System.out.println(resept.toString());
            System.out.println("Henter resept ID");
            System.out.println("ID: " + resept.hentId());
            System.out.println("Skriver ut reseptfarge");
            System.out.println("Farge: " + resept.farge());

            System.out.println("Skriver ut objektsklasse og pris til aa betale");
            System.out.println("Resept type: " + resept.getClass());
            System.out.println("Pris til aa betale: " + resept.prisAaBetale());
            System.out.println();
        }

        System.out.println("Sjekker bruk() metoden og skriver ut en resept paa nytt");
        System.out.println("Bruker en resept, skriver ut en resept(reit var 3, maa bli 2)");
        resepter.get(0).bruk();
        System.out.println(resepter.get(0).toString());

        */
    }
    public static boolean testFarge(String faktiskResultat, String forventetResultat) {
        if (faktiskResultat.equalsIgnoreCase(forventetResultat)){
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat.equalsIgnoreCase(forventetResultat);
    }

    public static boolean testPrisAaBetale(double faktiskResultat, double forventetResultat) {
        if (faktiskResultat == forventetResultat){
            System.out.println("Riktig " + teller);
        } else {
            System.out.println("Feil " + teller);
        }
        teller ++;
        return faktiskResultat == forventetResultat;
    }
}
