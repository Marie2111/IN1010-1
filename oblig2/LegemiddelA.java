public class LegemiddelA extends Legemiddel{
    protected int narkotiskStyrke;

    public LegemiddelA(String navn, double pris, double virkestoff, int narkotiskStyrke){
        super(navn, pris, virkestoff);
        this.narkotiskStyrke=narkotiskStyrke;
    }

    public int hentNarkotiskStyrke(){
        return narkotiskStyrke;
    }

    @Override
    public String toString(){
        return super.toString() + " N.styrke: " + narkotiskStyrke;
    }
}
