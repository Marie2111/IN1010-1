import java.util.Scanner;

public class Legesystem {
    private static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    private static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    private static Stabel<Resept> resepter = new Stabel<Resept>();
    private static SortertLenkeliste<Lege> leger = new SortertLenkeliste<>();
    private static Scanner inn = new Scanner(System.in);

    public static void main(String[] args) {
        Meny.Hovedmeny();
    }

    private static class Meny {
        private enum Hovedmeny {
            SKRIV_UT_MENY("skriver ut hovedmenyen"),
            LEGG_TIL("Legg til et nytt objekt"),
            SKRIV_UT_ALLE("Skriv ut alle pasienter/leger/legemiddler/resepter"),
            BRUK_RESEPT_TIL_PASIENT("Bruk en gitt resept fra listen til en pasient"),
            STATISTIKK("Skriv ut statistikk"),
            AVSLUTT("Avslutt programmet");

            private String beskrivelse;

            Hovedmeny(String beskrivelse) {
                thsi.beskrivelse = beskrivelse;
            }

            public String toString() {
                return String.format("[%d] %s", ordinal(), beskrivelse);
            }
        }

        private static void hovedmeny() {
            Hovedmeny valg = Hovedmeny.SKRIV_UT_MENY;
            Hovedmeny[] alternativer = Hovedmeny.values();
            int min = 0;
            int max = alternativer.length - 1;
            while(true) {
                switch(valg) {
                    case SKRIV_UT_MENY:
                        for(Hovedmeny alternativ: alternativer) {
                            System.out.println(alternativ);
                        }
                        break;

                    case LEGG_TIL:
                        menyLeggTil();
                        break;

                    case SKRIV_UT_ALLE:
                        menySkrivUtAlle();
                        break;

                    case BRUK_RESEPT_TIL_PASIENT:
                        menyBrukResept();
                        break;

                    case STATISTIKK:
                        menyStatistikk();
                        break;

                    case AVSLUTT:
                        return;

                }
                int nyttValgTall = IO.intFraBrukerFraTil(" Hovedmeny: ",
                                                         min, max);
                valg = alternativer[nyttValgTall];
            }
        }

        private enum menyLeggTil {
            SKRIV_UT_MENY("Skriv ut denne menyen"),
            PASIENT("Legg til en ny pasient"),
            LEGE("Legg til en ny lege"),
            LEGEMIDDEL("Legg til et nytt legemiddel"),
            RESEPT("Legg til en ny resept"),
            TILBAKE("Gå tilbake til hovedmenyen");

            private String beskrivelse;

            menyLeggTil(String beskrivelse) {
                this.beskrivelse = beskrivelse;
            }

            public String toString() {
                return String.format("[%d] %s", ordinal(), beskrivelse);
            }
        }

        private static void menyLeggTil() {
            Hovedmeny valg = MenyLeggTil.SKRIV_UT_MENY;
            Hovedmeny[] alternativer = Hovedmeny.values();
            int min = 0;
            int max = alternativer.length - 1;
            while(true) {
                switch(valg) {
                    case SKRIV_UT_MENY:
                        for(MenyLeggTil alternativ: alternativer) {
                            System.out.println(alternativ);
                        }
                        break;

                    case PASIENT:
                        leggTilPasient();

                    case LEGE:
                        leggTilLege();

                    case LEGEMIDDEL:
                        leggTilLegemiddel();

                    case RESEPT:
                        leggTilResept();

                    case TILBAKE:
                        return;
                }
                int nyttValgTall = IO.intFraBrukerFraTil("Legg til: ",
                                                         min, max);
                valg = alternativer[nyttValgTall];
            }
        }

        public static void leggTilPasient(){
            System.out.println("Hva er pasientens navn?");
            String navn = inn.nextLine();
            System.out.println("Hva er pasientens fodselsnummer?");
            String fnr = inn.nextLine();

            Pasient nyPasient = new Pasient(navn, fnr);
            return;
        }

        public static void leggTilLege() {
            System.out.println("Hva er legens navn?");
            Lege nyLege = new Lege(inn.nextLine());
            leger.settInnSortert(nyLege);
            return;
        }

        public static void leggTilLegemiddel() {
            System.out.println("Hva slags type legemiddel er det?");
            System.out.println("Er det:\n\t- LegemiddelA: tast \"a\"");
            System.out.println("\t- LegemiddelB: tast \"b\"");
            System.out.println("\t- LegemiddelC: tast \"c\"");

            String input = inn.nextLine();

            if (input.equals("a")) {
                System.out.println("Navnet til leggemiddel: ");
                String navn = inn.nextLine();

                System.out.println("Prisen: ");
                double pris = inn.nextDouble();

                System.out.println("Virkestoff: ");
                double virkestoff = inn.nextDouble();

                System.out.println("Narkotisk styrke: ");
                int styrke = inn.nextInt();

                LegemiddelA legemiddelA = new LegemiddelA(navn, pris, virkestoff, styrke);
                legemiddler.leggTil(legemiddelA);

            } else if (input.equals("b")) {
                System.out.println("Navnet til leggemiddel: ");
                String navn = inn.nextLine();

                System.out.println("Prisen: ");
                double pris = inn.nextDouble();

                System.out.println("Virkestoff: ");
                double virkestoff = inn.nextDouble();

                System.out.println("Vannedanende styrke: ");
                int styrke = inn.nextInt();

                LegemiddelB legemiddelB = new LegemiddelB(navn, pris, virkestoff, styrke);
                legemiddler.leggTil(legemiddelB);

            } else if (input.equals("c")) {
                System.out.println("Navnet til leggemiddel: ");
                String navn = inn.nextLine();

                System.out.println("Prisen: ");
                double pris = inn.nextDouble();

                System.out.println("Virkestoff: ");
                double virkestoff = inn.nextDouble();

                LegemiddelC legemiddelC = new LegemiddelC(navn, pris, virkestoff, styrke);
                legemiddler.leggTil(legemiddelC);

            } else {
                System.out.println("Feil bokstav");
            }
            return;
        }

        public static void leggTilResept() {
            System.out.println("Hvilken pasient skal utskrives en resept til?");
            Pasient pasient = null;
            String pasientensNavn = inn.nextLine();
            String pasientensFnr = inn.nesxtLine();

            for(Pasient p : pasienter) {
                if(p.hentNavn().equalsIgnoreCase(pasientensNavn) && p.hentFodselsnummer().equals(pasientensFnr)){
                    pasient = p;
                } else {
                    System.out.println("Pasienten finnes ikke!");
                }
                return p;
            }

            System.out.println("Hvilken lege utskriver denne resepten?");
            Lege lege = null;
            String legensNavn = inn.nextLine();

            for(Lege l : leger) {
                if (l.hentNavn().equalsIgnoreCase(legensNavn)) {
                    lege = l;
                } else {
                    System.out.println("Legen finnes ikke!");
                }
                return l;
            }

            System.out.println("Hvilket legemiddel skal brukes?");
            Legemiddel legemiddel = null;
            Strng legemidNavn = inn.nextLine();

            for(Legemiddel l : legemidler) {
                if(l.hentNavn().equalsIgnoreCase(legemidNavn)) {
                    legemiddel = l;
                } else {
                    System.out.println("Legemidlet finnes ikke!");
                }
                return l;
            }

            System.out.println("Hvor mange ganger kan resepten brukes?");
            int reit = inn.nextInt();

            System.out.println("Hva slags type resept er det?");
            System.out.println("Er det:\n\t- Blaa Resept: tast \"b\"");
            System.out.println("\t- P resept: tast \"p\"");
            System.out.println("\t- Militaer resept: tast \"m\"");

            String input = inn.nextLine();

            if(input.equals("b")) {
                BlaaResept bResept = new bResept(legemiddel, lege, pasient, reit);
                lege.leggTilResept(bResept);
                pasient.leggTilResept(bResept);
                resepter.leggPaa(bResept);
                System.out.println("Resepten ble lagret!");
                break;

            } else if (input.equals("p")) {
                PResept pResept = new PResept(legemiddel, lege, pasient, reit);
                lege.leggTilResept(pResept);
                pasient.leggTilResept(pResept);
                resepter.leggPaa(pResept);
                System.out.println("Resepten ble lagret!");
                break;

            } else if (input.equals("m")) {
                MilitaerResept mResept = new MilitaerResept(legemiddel, lege, pasient, reit);
                lege.leggTilResept(mResept);
                pasient.leggTilResept(mResept);
                resepter.leggPaa(mResept);
                System.out.println("Resepten ble lagret!");
                break;

            } else {
                System.out.println("Feil bokstav");
            }
        }

        private enum MenySkrivUtAlle {
            SKRIV_UT_MENY("Skriv ut denne menyen"),
            PASIENTER("Skriv ut alle pasienter og pasientens resepter"),
            LEGER("Skriv ut alle leger og legens resepter"),
            LEGEMIDLER("Skriv ut alle legemidler"),
            TILBAKE("Gå tilbake til hovedmenyen");

            private String beskrivelse;

            MenySkrivUtAlle(String beskrivelse) {
                this.beskrivelse = beskrivelse;
            }

            public String toString() {
                return String.format("[%d] %s", ordinal(), beskrivelse);
            }
        }

        private static void menySkrivUtAlle() {
            MenySkrivUtAlle valg = MenySkrivUtAlle.SKRIV_UT_MENY;
            MenySkrivUtAlle[] alternativer = MenySkrivUtAlle.values();
            int min = 0;
            int max = alternativer.length - 1;
            while(true) {
                switch(valg) {
                    case SKRIV_UT_ALLE:
                        for(MenySkrivUtAlle alternativ: alternativer) {
                            System.out.println(alternativ);
                        }
                        break;

                    case PASIENTER:
                        System.out.println("OVERSIKT OVER PASIENTENE");
                        for(Pasient pasient: pasienter) {
                            p.toString();
                            System.out.println("Pasientens reseptliste:");
                            for (Resept resept : pasient.hentReseptliste()) {
                                System.out.println(resept.toString());
                            }
                            System.out.println("\n\n");
                        }
                        break;

                    case LEGER:
                        System.out.println("OVERSIKT OVER LEGENE");
                        for(Lege lege: leger) {
                            lege.toString();
                            System.out.println("Legens reseptliste:");
                            for (Resept resept : lege.hentReseptliste()) {
                                System.out.println(resept.toString());
                            }
                            System.out.println("\n\n");
                        }
                        break;

                    case LEGEMIDLER:
                        System.out.println("OVERSIKT OVER LEGEMIDLENE:");
                        for(Legemiddel legemiddel: legemidler) {
                            System.out.println(legemiddel.toString());
                        }
                        break;

                    case TILBAKE:
                        return;
                }
                int nyttValgTall = IO.intFraBrukerFraTil("Skriv ut: ",
                                                         min, max);
                valg = alternativer[nyttValgTall];
            }
        }

        private static void menyBrukResept(){
            if(pasienter.erTom()) {
                System.out.println("Det finnes ingen pasienter.");
                return;
            }

            System.out.println("Hvilken pasient vil du se resepter for?");

            for (Pasient pasient: pasienter) {
                System.out.println(pasient.hentId() + ": "+ pasient.toString());
            }

            String pasientensID = inn.nextLine();

            Pasient pasienten = null;
            for (Pasinet p : pasienter) {
                if(p.hentID() == pasientensID){
                    pasienten = p;
                    for (Resept resept : pasienten.hentReseptliste()) {
                        System.out.println(resept);
                    }
                }
            }

            if (pasienten != null) {
                System.out.println("Hva er id'en til resepten som skla brukes?");
                int reseptensID = inn.nextInt();

                for (Resept resept : pasienten.hentReseptliste()) {
                    if (resept.hentId() == reseptensID) {
                        if(resept.hentReit() > 0) {
                            resept.bruk();
                            System.out.println("Brukte resept paa " + resept.hentLegemiddel() +
                                                ". Antall gjenvarende reit: " + resept.hentReit());
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

        private enum MenyStatistikk {
            SKRIV_UT_MENY("Skriv ut denne menyen"),
            RESEPT("Skriv ut antall utskrevne resepter på vanedannende legemidler"),
            PASIENT("Skriv ut alle blå resepter tilhørende en bestemt pasient"),
            LEGE("Skriv ut alle blå resepter tilhørende en bestemt lege"),
            TILBAKE("Gå tilbake til hovedmenyen");

            private String beskrivelse;

            MenyStatistikk(String beskrivelse) {
                this.beskrivelse = beskrivelse;
            }

            public String toString() {
                return String.format("[%d] %s", ordinal(), beskrivelse);
            }
        }

        private static void menyStatistikk() {
            MenyStatistikk menyvalg = MenyStatistikk.SKRIV_UT_MENY;
            MenyStatistikk[] alternativer = MenyStatistikk.values();
            int min = 0;
            int max = alternativer.length - 1;
            while(true) {
                switch(valg) {
                    case SKRIV_UT_ALLE:
                        for(MenyStatistikk alternativ: alternativer) {
                            System.out.println(alternativ);
                        }
                        break;

                    case RESEPTER:
                        statistikkResept();
                        return;

                    case PASIENT:
                        statistikkPasient();
                        return;

                    case LEGE:
                        statistikkLege();
                        return;

                    case TILBAKE:
                        return;
                }
                int nyttValgTall = IO.intFraBrukerFraTil("Skriv ut: ",
                                                         min, max);
                valg = alternativer[nyttValgTall];
            }
        }

        private static void statistikkResept() {
            if (resepter.erTom()) {
                System.out.println("Det finnes ingen resepter");
                return;
            }
            int hvitResepter = 0;
            for(Resept resept: resepter){
                if(resept instanceof HvitResept) {
                    hvitResepter++;
                }
            }
            if (hvitResepter == 0) {
                System.out.println("Det finnes ingen hvit resepter." );
            } else {
                System.out.printf("Det ble utskrevet %d resept(er) totalt:\n",
                                                                    hvitResepter);
            }

            int militaerResepter = 0;
            for(Resept resept: resepter) {
                if (resept instanceof MilitaerResept) {
                    militaerResepter++;
                }
            }
            if (hvitResepter == 0) {
                System.out.println("Det finnes ingen militaer resepter." );
            } else {
                System.out.printf("Det ble utskrevet %d militaer resept(er) totalt:\n",
                                                                    militaerResepter);
            }
        }

        private static void statistikkPasient(){
            if (pasienter.erTom()) {
                System.out.println("Det finnes ingen pasienter");
                return;
            }
            int blaaResepter = 0;
            for (Pasient pasient: pasienter) {
                for (Resept resept : pasient.hentReseptliste()) {
                    if(resept instanceof BlaaResept) {
                        blaaResepter++;
                    }
                }
                System.out.println(pasient.hentNavn() + blaaResepter);
            }
        }

        private static void statistikkLege(){
            if(leger.erTom()) {
                System.out.println("Det finnes ingen leger");
                return;
            }
            int blaaResepter = 0;
            for (Lege lege : leger) {
                for (Resept resept : lege.hentReseptliste()) {
                    if(resept instanceof BlaaResept) {
                        blaaResepter++;
                    }
                }
            System.out.println(lege.hentNavn() + blaaResepter);
            }
        }



    }
}
