abstract public class Resept{

    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId;
    protected int reit;
    protected int id;
    protected static int teller=0;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        this.legemiddel=legemiddel;
        this.utskrivendeLege=utskrivendeLege;
        this.pasientId=pasientId;
        this.reit=reit;
        id=teller++;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public int hentPasientId() {
        return pasientId;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if(reit!=0){
            reit--;
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "ID " + id + ", Lege: " + utskrivendeLege + ", Legemiddel: " + legemiddel + ", Reit: " + reit +", Pasient ID: " + pasientId;
    }

    abstract public String farge();

    abstract public double prisAaBetale();
    
}
