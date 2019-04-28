import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.io.*;

public class Etterretningssentral {
    private static final int ANTALL_TELEGRAFISTER = 3;
    private static final int ANTALL_KRYPTOGRAFER = 5;

    public static void main(String[] args) {
        Operasjonssentral ops = new Operasjonssentral(ANTALL_TELEGRAFISTER); //oppretter en operasjonssentral med like mange kanaler som AT
        Kanal[] kanaler = ops.hentKanalArray(); //henter kanalene
        KryptertMonitor kMonitor = new KryptertMonitor(ANTALL_TELEGRAFISTER); //sender med antall telegrafister
        DekryptertMonitor dMonitor = new DekryptertMonitor(ANTALL_KRYPTOGRAFER); //sender med antall kryptografer

        //starter opp telegrafistene
        int i = 0;
        while (i < ANTALL_TELEGRAFISTER) {
            Runnable telegrafist = new Telegrafist(kanaler[i], kMonitor);
            new Thread(telegrafist).start();
            i++;
        }

        //starter opp kryptografene
        int j = 0;
        while (j < ANTALL_KRYPTOGRAFER) {
            Runnable kryptograf = new Kryptograf(kMonitor, dMonitor);
            new Thread(kryptograf).start();
            j++;
        }

        //starter opp operasjonslederen
        Runnable operasjonsleder = new Operasjonsleder(dMonitor, ANTALL_TELEGRAFISTER);
        new Thread(operasjonsleder).start();
    }
}

class Melding implements Comparable<Melding> {
    private String innhold;
    private int sekvensnummer;
    private int kanalId;
    static int teller = 0;

    public Melding(int kanalId, String innhold) {
        this.innhold = innhold;
        this.kanalId = kanalId;
        sekvensnummer = teller++;
    }

    public void dekryptering() {
        this.innhold = Kryptografi.dekrypter(this.innhold);
    }

    public String hentInnhold() {
        return innhold;
    }

    public int hentSekvensnummer() {
        return sekvensnummer;
    }

    public int hentKanalId() {
        return kanalId;
    }

    @Override
    public int compareTo(Melding annenMelding) {
      return this.sekvensnummer-annenMelding.sekvensnummer;
    }
}

class Telegrafist implements Runnable {
    private Melding melding;
    private KryptertMonitor kMonitor;
    private Kanal kanal;

    public Telegrafist(Kanal kanal, KryptertMonitor kMonitor) {
        this.kanal = kanal;
        this.kMonitor = kMonitor;
    }

    public void run() {
        String innholdet = " ";
        while (innholdet != null) {
            kMonitor.laasMonitor();
            try {
                innholdet = kanal.lytt();
                kMonitor.sendMelding(new Melding(kanal.hentId(), innholdet));
            } finally {
                kMonitor.apneMonitor();
            }
        }
    }
}

class Kryptograf implements Runnable {
    private KryptertMonitor kMonitor;
    private DekryptertMonitor dMonitor;
    private Melding melding;

    public Kryptograf(KryptertMonitor kMonitor, DekryptertMonitor dMonitor) {
        this.kMonitor = kMonitor;
        this.dMonitor = dMonitor;
    }

    public void run() {
        melding = new Melding(0, "test");
        while (melding != null) {
            kMonitor.laasMonitor();

            try {
                melding = kMonitor.hentMelding();
            } catch (InterruptedException e) {

            } finally {
                kMonitor.apneMonitor();
            }

            if (melding != null) {
                melding.dekryptering();
            }
            dMonitor.laasMonitor();

            try {
                dMonitor.sendMelding(melding);
            } finally {
                dMonitor.apneMonitor();
            }
        }
    }
}

class KryptertMonitor {
    private final Lock laas = new ReentrantLock();
    private final Condition apneKanaler = laas.newCondition();
    private Queue<Melding> koe = new LinkedList<Melding>();
    private int stengteKanaler = 0;
    private int antallTelegrafister;

    public KryptertMonitor(int antallTelegrafister) {
        this.antallTelegrafister = antallTelegrafister;
    }

    public void laasMonitor() {
        laas.lock();
    }

    public void apneMonitor() {
        laas.unlock();
    }

    public void sendMelding(Melding melding) {
        if (melding.hentInnhold() == null) {
            stengteKanaler++;
        } else {
            koe.add(melding);
            apneKanaler.signalAll();
        }
    }

    public Melding hentMelding() throws InterruptedException {
        if (koe.isEmpty()) {
            if (stengteKanaler < antallTelegrafister) {
                apneKanaler.await();
                return koe.remove();
            } else {
                return null;
            }
        } else {
            return koe.remove();
        }
    }
}

class DekryptertMonitor {
    private final Lock laas = new ReentrantLock();
    private final Condition kryptografLaas = laas.newCondition();
    private SortertLenkeliste<Melding> meldinger = new SortertLenkeliste<Melding>();
    private int antallKryptografer;
    private int ferdigeKryptografer;

    public DekryptertMonitor(int antallKryptografer) {
      this.antallKryptografer = antallKryptografer;
    }

    public void laasMonitor() {
      laas.lock();
    }

    public void apneMonitor() {
      laas.unlock();
    }

    public void sendMelding(Melding melding) {
        if (melding != null) {
            meldinger.leggTil(melding);
        } else {
            ferdigeKryptografer++;
            if (ferdigeKryptografer == antallKryptografer) {
                kryptografLaas.signalAll();
            }
        }
    }

    public ArrayList<Melding> hentMeldinger(int kanalID) {
        ArrayList<Melding> sorterteMeldinger = new ArrayList<Melding>();
        if (ferdigeKryptografer < antallKryptografer) {
            try {
                kryptografLaas.await();
            }
            catch(InterruptedException e) {}
        }

        for (Melding melding : meldinger) {
            if (melding.hentKanalId() == kanalID){
                sorterteMeldinger.add(melding);
                kryptografLaas.signalAll();
            }
        }
        return sorterteMeldinger;
    }
}

class Operasjonsleder implements Runnable {
    private DekryptertMonitor dMonitor;
    private int antallTelegrafister;
    private ArrayList<ArrayList<Melding>> kanaler = new ArrayList<ArrayList<Melding>>();

    public Operasjonsleder(DekryptertMonitor dMonitor, int antallTelegrafister) {
        this.dMonitor = dMonitor;
        this.antallTelegrafister = antallTelegrafister;
    }

    public void run() {
        int i = 1;
        while (kanaler.size() < antallTelegrafister) {
            dMonitor.laasMonitor();
            try {
                kanaler.add(dMonitor.hentMeldinger(i));
                i++;
            }
            finally {
                dMonitor.apneMonitor();
            }
            try {
                this.printTilFil();
            }
            catch (Exception e) {}
        }
    }

    private void printTilFil() throws Exception {
        PrintWriter pw;
        for (ArrayList<Melding> liste : kanaler){
            pw = new PrintWriter("fil"+Integer.toString(kanaler.indexOf(liste)), "utf-8");
            for (Melding m : liste){
                pw.println(m.hentInnhold() + "\n");
            }
            pw.close();
        }
    }
}
