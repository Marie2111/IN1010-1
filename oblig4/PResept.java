public class PResept extends HvitResept{
    protected static int rabatt=116;

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
        super(legemiddel, utskrivendeLege, p, 3);
    }

    @Override
    public double prisAaBetale(){
        double pris = legemiddel.hentPris();
        if(pris>=116){
            return pris-rabatt;
        } else {
            return 0;
        }
    }
}
