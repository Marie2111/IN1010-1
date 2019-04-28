public class SortertLenkeliste<T extends Comparable<T> > extends Lenkeliste <T>{
  /*skal altså sette inn elementer i sortert rekkefølge (fra minst til størst)*/

  @Override
  public   void  leggTil ( T x ){
    settInnSortert(x);
  }

  public void settInnSortert(T x){
    Node ny = new Node(x);
    Node tmp = foran;

    if(erTom()){
      foran = ny;
      sist=ny;
      stoerrelse++;
      return;
    }

    if(tmp.data.compareTo(ny.data) >= 0){
      ny.neste = tmp;
      tmp.forrige = ny;
      foran = ny;
      stoerrelse++;
      return;
    }

    while(tmp != null){
      // Siste node
      if(tmp.neste == null){
        tmp.neste = ny;
        ny.forrige = tmp;
        sist = ny;
        stoerrelse++;
        return;
        //Setter mellom
      } else if(tmp.neste.data.compareTo(ny.data) > 0){
        ny.forrige = tmp;
        ny.neste = tmp.neste;
        tmp.neste.forrige = ny;
        tmp.neste = ny;
        stoerrelse++;
        return;
      } else {
        tmp = tmp.neste;
      }
    }
  }

  @Override
  public T fjern(){
    Node tmp = sist;

    if(erTom()){
      throw new UgyldigListeIndeks(0);
    } else if(foran == sist){
      foran = null;
      sist = null;
    } else {
      sist = sist.forrige;
      if (sist != null){
        sist.neste = null;
      }
    }
    stoerrelse--;
    return tmp.data;
  }

  /*De nye implementasjonene skal ikke sette inn elementet,
  men istedet kun kaste et unntak som finnes i Java fra før av:
  UnsupportedOperationException  (dette trenger ikke å importeres)*/
  @Override
  public   void  leggTil (int pos, T x){
    throw new UnsupportedOperationException();
  }

  @Override
  public   void  sett (int pos, T x){
    throw new UnsupportedOperationException();
  }
}
