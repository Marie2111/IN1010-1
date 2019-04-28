public class HvitResept extends Resept{

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege,pasientId, reit);
    }

    @Override
    public String farge(){
        return "hvit";
    }

    public double prisAaBetale(){
        return legemiddel.hentPris();
    }
}
