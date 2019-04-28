import java.util.ArrayList;

public class Integrasjonstest{

    public static void main(String [] args){
        ArrayList<Resept> resepter = new ArrayList<Resept>();
        resepter.add(new PResept(new LegemiddelA("Cerazette", 150, 1.2, 3), new Lege("Dr.Olavson"), 1, 3));
        resepter.add(new MilitaerResept(new LegemiddelB("Paracet", 180, 2, 3), new Fastlege("Dr.Grodaas", 12345), 2, 1));
        resepter.add(new BlaaResept(new LegemiddelC("Sedix", 250, 2), new Lege("Dr.Brunne"), 3, 2));

        System.out.println("Leger:");

        for(Resept resept: resepter){
        System.out.println(resept.hentLege());
        }

        System.out.println();
        System.out.println("Legemidler:");

        for(Resept resept: resepter){
        System.out.println(resept.hentLegemiddel().getClass());
        System.out.println(resept.hentLegemiddel());
        }

        System.out.println();
        System.out.println("Resepter:");

        for(Resept resept: resepter){
        System.out.println(resept.getClass());
        System.out.println(resept.toString());
        }
    }
}
