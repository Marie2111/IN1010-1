public class PResept extends HvitResept{
    protected static int rabatt=116;

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege,pasientId, 3);
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
