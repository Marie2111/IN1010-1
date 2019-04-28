public class Pasient {
    private String navn;
    private long fodselsnummer;
    private int id;
    private static int teller=0;
    private Stabel<Resept> stabel = new Stabel<Resept>();

    public Pasient(String navn, long fodselsnummer) {
        this.navn=navn;
        this.fodselsnummer=fodselsnummer;
        stabel = new Stabel<Resept>();
        id=teller++;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public long hentFodselsnummer() {
        return fodselsnummer;
    }

    public void leggTilResept(Resept resept) {
        stabel.leggPaa(resept);
    }

    public Stabel<Resept> hentReseptListe() {
        return stabel;
    }

    @Override
    public String toString() {
        return navn + " (fnr " + fodselsnummer + ")" ;
    }
}
