class Fastlege extends Lege implements Kommuneavtale {

    protected int avtalenummer;

    public Fastlege(String navn, int avtalenummer){
        super(navn);
        this.avtalenummer=avtalenummer;
    }

    public int hentAvtalenummer(){
        return avtalenummer;
    }

    @Override
    public String toString(){
        return navn + ", avtalenr " + avtalenummer;
    }
}
