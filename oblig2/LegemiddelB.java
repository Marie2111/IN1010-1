public class LegemiddelB extends Legemiddel{
    protected int vanedannedeStyrke;

    public LegemiddelB(String navn, double pris, double virkestoff, int vanedannedeStyrke){
        super(navn, pris, virkestoff);
        this.vanedannedeStyrke=vanedannedeStyrke;
    }

    public int hentVanedannedeStyrke(){
        return vanedannedeStyrke;
    }

    @Override
    public String toString(){
        return super.toString() + " V.styrke: " + vanedannedeStyrke;
    }
}
