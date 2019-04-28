public class Stabel<T> extends Lenkeliste<T>{
  /*Disse metodene skal henholdsvis legge til elementer paa slutten av listen,
   slik at det siste elementet som legges inn er det første som tas ut (Last in, First out).
   Merk: Det forventes her at du tar i bruk metodene som er arvet fra Lenkeliste<T>*/
  public void leggPaa(T x){
    super.leggTil(x);
  }

  //fjerner elementer fra slutten av listen
  public T taAv(){
    Node tmp = sist;
    if(erTom()){
      throw new UgyldigListeIndeks(0);
    } else if(foran == sist){
      foran = null;
      sist = null;
    } else {
      sist = sist.forrige;
      sist.neste = null;
    }
    stoerrelse--;
    return tmp.data;
  }
}
