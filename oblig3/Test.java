import java.util.Iterator;
public class Test{

public static void main(String[] args) {

  Lenkeliste<Integer> listeS = new SortertLenkeliste<>();

  listeS.leggTil(1);
  listeS.leggTil(3);
  listeS.leggTil(5);
  listeS.leggTil(2);
  listeS.leggTil(0);
  listeS.leggTil(4);
  listeS.leggTil(6);
  listeS.leggTil(8);
  listeS.leggTil(7);
  listeS.fjern();

  Iterator<Integer> it = listeS.iteratorForan();
  System.out.println("Itererer fram: ");
  while(it.hasNext()){
    System.out.println(it.next());
  }

  /*Lenkeliste<Integer>liste = new Lenkeliste<>();
  liste.leggTil(1);
  liste.leggTil(2);
  liste.leggTil(3);
  //liste.leggTil(0, 5);
  //liste.sett(0,3);
  //liste.leggTilIStart(4);
  //liste.fjern();
  //liste.fjern(2);

    Iterator<Integer> it = liste.iteratorForan();
    System.out.println("Itererer fram: ");
    while(it.hasNext()){
      System.out.println(it.next());
    }

    Iterator<Integer> it2 = liste.iteratorSist();
    System.out.println("Itererer tilbake: ");
    while(it2.hasNext()){
      System.out.println(it2.next());
    }


    System.out.println("stoerrelse: " + liste.stoerrelse());
    System.out.println("henter et element paa plass 3: " + liste.hent(2));
    System.out.println("Er tom: " + liste.erTom());
*/
  }
}
