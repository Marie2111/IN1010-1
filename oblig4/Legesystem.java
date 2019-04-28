import java.util.Scanner;

public class Legesystem {
    private static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    private static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    private static SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    private static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
    //private static Scanner inn = new Scanner (System.in);

    public static void main(String[] args) {
        hovedmeny();
    }

    public static void hovedmeny() {
        String valg = " ";

        do{
            System.out.println("");
            System.out.println("HOVEDMENY");
            System.out.println("1: Skriv ut alle");
            System.out.println("2: Legg til nye elementer");
            System.out.println("3: Bruk av resept");
            System.out.println("4: Statistikk");
            System.out.println("5: Avslutt");

            Scanner dinevalg = new Scanner (System.in);
            valg = dinevalg.nextLine();

            switch(valg) {
                case "1":
                    oversikt();
                    break;

                case "2":
                    leggTil();
                    break;

                case "3":
                    brukResept();
                    break;

                case "4":
                    statistikk();
                    break;

                case "5":
                    System.out.println("Programmet avsluttes.");
                    System.exit(0);

                default:
                    System.out.println("Feil verdi.");
            }
        } while (!valg.equals("6"));
    }

    public static void oversikt(){
        System.out.println("OVERSIKT OVER PASIENTER: ");
        if (pasienter.erTom()) {
            System.out.println("Det finnes ingen pasienter.");
        }

        for (Pasient pasient : pasienter) {
            System.out.println(pasient.toString());
            System.out.println("Pasientens reseptliste: ");
            if (pasient.hentReseptListe().erTom()) {
                System.out.println("Det finnes ingen resepter.");
            } else {
                for (Resept resept : pasient.hentReseptListe()) {
                    System.out.println(resept.toString());
                }
            }
        }
            System.out.println("\n");

        System.out.println("OVERSIKT OVER LEGER: ");
        if (leger.erTom()) {
            System.out.println("Det finnes ingen leger.");
        }

        for (Lege lege : leger) {
            System.out.println(lege.toString());
            System.out.println("Legens reseptliste: ");
            if (lege.hentReseptListe().erTom()) {
                System.out.println("Det finnes ingen resepter.");
            } else {
                for (Resept resept : lege.hentReseptListe()) {
                    System.out.println(resept.toString());
                }
            }
        }
        System.out.println("\n");

        System.out.println("OVERSIKT OVER LEGEMIDLER: ");
        if (legemidler.erTom()) {
            System.out.println("Det finnes ingen legemidler.");
        }

        for (Legemiddel legemiddel : legemidler) {
            System.out.println(legemiddel.toString());
        }
    }

    public static void leggTil(){
        System.out.println("Ønkser du å legge til:\n\t- Lege, tast \"1\"");
        System.out.println("\t- Pasient, tast \"2\"\n\t- Legemiddel, tast \"3\"");
        System.out.println("\t- Resept, tast \"4\"");
        Scanner in = new Scanner (System.in);
        String nyValg = in.nextLine();

        if (nyValg.equals("1")) {
            leggTilLege();

        } else if (nyValg.equals("2")) {
            leggTilPasient();

        } else if (nyValg.equals("3")) {
            leggTilLegemiddel();

        } else if (nyValg.equals("4")) {
            leggTilResept();

        } else {
            System.out.println("Feil verdi, prov igjen.");
        }
    }

    public static void leggTilLege(){
        Scanner lege = new Scanner(System.in);
        System.out.println("Hva er legens navn? ");
        String navn = lege.nextLine();
        System.out.println("Er denne legen en fastlege?");
        System.out.println("\t- Ja: tast \"1\"");
        System.out.println("\t- Nei: tast \"2\"");

        String legeValg = lege.nextLine();

        if(legeValg.equals("1")) {
            System.out.println("Tast inn et avtalenummer ");
            int avtalenummer = lege.nextInt();
            lege.nextLine();
            Lege nyLege = new Fastlege(navn, avtalenummer);
            leger.leggTil(nyLege);

        } else if(legeValg.equals("2")) {
            Lege nyLege = new Lege(navn);
            leger.leggTil(nyLege);
        }
        System.out.println("Legen ble lagret!");
    }

    public static void leggTilPasient(){
        Scanner pasient = new Scanner(System.in);

        System.out.println("Hva er pasientens navn? ");
        String navn = pasient.nextLine();

        System.out.println("Hva er pasientens fodselsnummer? ");
        long fnr = pasient.nextLong();
        pasient.nextLine();

        Pasient nyPasient = new Pasient (navn, fnr);
        pasienter.leggTil(nyPasient);
        System.out.println("Pasienten ble lagret!");
    }

    public static void leggTilLegemiddel(){
        System.out.println("Hva slags type legemiddel er det?");
        System.out.println("Er det:\n\t- LegemiddelA: tast \"a\"");
        System.out.println("\t- LegemiddelB: tast \"b\"");
        System.out.println("\t- LegemiddelC: tast \"c\"");

        Scanner tastatur = new Scanner(System.in);
        String nyInput = tastatur.nextLine();

        if (nyInput.equals("a")) {
            System.out.println("Navnet til leggemiddel: ");
            String navn = tastatur.nextLine();

            System.out.println("Prisen: ");
            double pris = tastatur.nextDouble();
            tastatur.nextLine();

            System.out.println("Virkestoff: ");
            double virkestoff = tastatur.nextDouble();
            tastatur.nextLine();

            System.out.println("Narkotisk styrke: ");
            int styrke = tastatur.nextInt();
            tastatur.nextLine();

            LegemiddelA legemiddelA = new LegemiddelA(navn, pris, virkestoff, styrke);
            legemidler.leggTil(legemiddelA);
            System.out.println("Legemidlet ble lagret!");

        } else if (nyInput.equals("b")) {
            System.out.println("Navnet til leggemiddel: ");
            String navn = tastatur.nextLine();

            System.out.println("Prisen: ");
            double pris = tastatur.nextDouble();
            tastatur.nextLine();

            System.out.println("Virkestoff: ");
            double virkestoff = tastatur.nextDouble();
            tastatur.nextLine();

            System.out.println("Vannedanende styrke: ");
            int styrke = tastatur.nextInt();
            tastatur.nextLine();

            LegemiddelB legemiddelB = new LegemiddelB(navn, pris, virkestoff, styrke);
            legemidler.leggTil(legemiddelB);
            System.out.println("Legemidlet ble lagret!");

        } else if (nyInput.equals("c")) {
            System.out.println("Navnet til leggemiddel: ");
            String navn = tastatur.nextLine();

            System.out.println("Prisen: ");
            double pris = tastatur.nextDouble();
            tastatur.nextLine();

            System.out.println("Virkestoff: ");
            double virkestoff = tastatur.nextDouble();
            tastatur.nextLine();

            LegemiddelC legemiddelC = new LegemiddelC(navn, pris, virkestoff);
            legemidler.leggTil(legemiddelC);
            System.out.println("Legemidlet ble lagret!");

        } else {
            System.out.println("Feil bokstav");
        }
    }

    public static void leggTilResept(){
        Scanner scanner = new Scanner(System.in);

        if (pasienter.erTom()) {
            System.out.println("Det finnes ingen pasienter! Resept kan ikke oprettes.");
            return;
        }

        System.out.println("Hvilken pasient skal utskrives en resept til?");
        for (Pasient pasient : pasienter) {
            System.out.println(pasient.hentId() + ": " + pasient.toString());
        }

        Pasient pasient = null;
        int pasientensId = scanner.nextInt();
        scanner.nextLine();

        for (Pasient p : pasienter) {
            if (p.hentId() == pasientensId) {
                pasient = p;

            } else {
                System.out.println("Pasienten finnes ikke.");
                return;
            }
        }

        if (leger.erTom()) {
            System.out.println("Det finnes ingen leger! Resept kan ikke oprettes.");
            return;
        }

        System.out.println("Hvilken lege utskriver denne resepten? ");
        for (Lege lege : leger) {
            System.out.println(lege.toString());
        }
        Lege lege = null;
        String legensNavn = scanner.nextLine();

        for (Lege l : leger) {
            if (l.hentNavn().equalsIgnoreCase(legensNavn)) {
                lege = l;

            } else {
                System.out.println("Legen finnes ikke.");
            }
        }

        if (legemidler.erTom()) {
            System.out.println("Det finnes ingen legemidler! Resept kan ikke oprettes.");
            return;
        }

        System.out.println("Hvilket legemiddel skal brukes?");
        for (Legemiddel legemiddel : legemidler) {
            System.out.println(legemiddel.toString());
        }
        Legemiddel legemiddel = null;
        String legemiddelNavn = scanner.nextLine();

        for (Legemiddel lm : legemidler) {
            if (lm.hentNavn().equalsIgnoreCase(legemiddelNavn)) {
                legemiddel = lm;

            } else {
                System.out.println("Legemidlet finnes ikke.");
                return;
            }
        }

        System.out.println("Hvor mange ganger kan resepten brukes?");
        int reit = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Hva slags type resept er det?");
        System.out.println("Er det:\n\t- Blaa Resept: tast \"b\"");
        System.out.println("\t- P resept: tast \"p\"");
        System.out.println("\t- Militaer resept: tast \"m\"");

        String in = scanner.nextLine();

        if(in.equals("b")) {
            Resept bResept = new BlaaResept(legemiddel, lege, pasient, reit);
            lege.leggTilResept(bResept);
            pasient.leggTilResept(bResept);
            resepter.leggTil(bResept);
            System.out.println("Resepten ble lagret!");

        } else if (in.equals("p")) {
            Resept pResept = new PResept(legemiddel, lege, pasient, reit);
            lege.leggTilResept(pResept);
            pasient.leggTilResept(pResept);
            resepter.leggTil(pResept);
            System.out.println("Resepten ble lagret!");

        } else if (in.equals("m")) {
            Resept mResept = new MilitaerResept(legemiddel, lege, pasient, reit);
            lege.leggTilResept(mResept);
            pasient.leggTilResept(mResept);
            resepter.leggTil(mResept);
            System.out.println("Resepten ble lagret!");

        } else {
            System.out.println("Feil bokstav");
        }
    }

    public static void brukResept() {
        Scanner bruk = new Scanner(System.in);

        if(pasienter.erTom()) {
            System.out.println("Det finnes ingen pasienter.");
            return;
        }

        System.out.println("Hvilken pasient vil du se resepter for?");

        for (Pasient pasient: pasienter) {
            System.out.println(pasient.hentId() + ": "+ pasient.toString());
        }

        int pasientensID = bruk.nextInt();
        bruk.nextLine();

        Pasient pasienten = null;
        for (Pasient p : pasienter) {
            if(p.hentId() == pasientensID){
                pasienten = p;
                for (Resept resept : pasienten.hentReseptListe()) {
                    System.out.println(resept);
                }
            }
        }

        if (pasienten != null || !pasienten.hentReseptListe().erTom()) {
            System.out.println("Hva er id'en til resepten som skla brukes?");
            int reseptensID = bruk.nextInt();
            bruk.nextLine();

            for (Resept resept : pasienten.hentReseptListe()) {
                if (resept.hentId() == reseptensID) {
                    if(resept.hentReit() > 0) {
                        resept.bruk();
                        System.out.println("Brukte resept paa %s . Antall gjenvarende reit %d \n",
                                                    resept.hentLegemiddel(), resept.hentReit());
                    } else {
                        System.out.println("Resepten kan ikke brukes flere ganger.");
                    }
                } else {
                    System.out.println("Resepten finnes ikke.");
                }
            }
        } else {
            System.out.println("Pasienten finnes ikke.");
        }
    }

    public static void statistikk() {
        if (resepter.erTom()) {
            System.out.println("Det finnes ingen resepter");
            return;
        }

        int vResepter = 0;
        for(Resept resept: resepter){
            if(resept.hentLegemiddel() instanceof LegemiddelB) {
                vResepter++;
            }
        }
        
        if (vResepter == 0) {
            System.out.println("Det finnes ingen vannedanende resepter." );

        } else {
            System.out.printf("Det ble utskrevet %d  vannedanende resept(er) totalt:\n",
                                                                vResepter);
        }

        int militaerResepter = 0;
        for(Resept resept: resepter) {
            if (resept instanceof MilitaerResept && resept.hentLegemiddel() instanceof LegemiddelB) {
                militaerResepter++;
            }
        }
        if (militaerResepter == 0) {
            System.out.println("Det finnes ingen militaer resepter." );

        } else {
            System.out.printf("Det ble utskrevet %d militaer resept(er) totalt:\n",
                                                                militaerResepter);
        }

        System.out.println("Pasienter som har blaa resepter og antall resepter per pasient");
        if (pasienter.erTom()) {
            System.out.println("Det finnes ingen pasienter");
            return;
        }

        int blaaResepterPasient = 0;
        for (Pasient pasient: pasienter) {
            for (Resept resept : pasient.hentReseptListe()) {
                if(resept instanceof BlaaResept) {
                    blaaResepterPasient++;
                    System.out.printf("Navn: %s. Antall blaa resepter: %d \n", pasient.hentNavn(), blaaResepterPasient);
                }
            }
        }

        System.out.println("Leger som har blaa resepter og antall resepter per lege");
        if(leger.erTom()) {
            System.out.println("Det finnes ingen leger");
            return;
        }

        int blaaResepterLege = 0;
        for (Lege lege : leger) {
            for (Resept resept : lege.hentReseptListe()) {
                if(resept instanceof BlaaResept) {
                    blaaResepterLege++;
                    System.out.printf("Navn: %s. Antall blaa resepter: %d \n", lege.hentNavn(), blaaResepterLege);
                }
            }
        }
    }
}
