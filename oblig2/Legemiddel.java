public abstract class Legemiddel{
    protected String navn;
    protected int id;
    protected double pris;
    protected double virkestoff;
    protected static int teller=0;

    public Legemiddel(String navn, double pris, double virkestoff){
        this.navn=navn;
        this.pris=pris;
        this.virkestoff=virkestoff;
        id=teller++;
    }

    public int hentId(){
        return id;
    }

    public String hentNavn(){
        return navn;
    }

    public double hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    @Override
    public String toString(){
        return navn + " (pris: " + pris + " "+ ", virkestoff: " + virkestoff + ")";
    }

    public double settNyPris(double nyPris){
        pris=nyPris;
        return nyPris;
    }
}
