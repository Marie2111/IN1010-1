import java.util.Iterator;

public class Lenkeliste <T> implements Liste<T>{
  protected Node foran;
  protected Node sist;
  protected int stoerrelse = 0;

  protected class Node{
    T data;
    Node neste = null;
    Node forrige = null;

    public Node(T data){
      this.data=data;
    }
  }

  public Lenkeliste(){
    this.foran = null;
    this.sist = null;
    this.stoerrelse = 0;
  }

  public int stoerrelse (){
    return stoerrelse;
  }

  public boolean erTom(){
    return foran == null;
  }

  public void leggTilIStart(T x){
    Node ny = new Node(x);

    if(erTom()){
      sist = ny;
    } else {
      foran.forrige = ny;
    }
    ny.neste = foran;
    foran = ny;
    stoerrelse++;
  }

  //skal legge inn et nytt element i listen og skyve neste element ett hakk lenger bak
  public void leggTil (int pos, T x){
    Node tmp = new Node(x);

    if(pos < 0 || pos > stoerrelse ){
      throw new UgyldigListeIndeks(pos);
    } else if (pos == 0){
      leggTilIStart(x);
    }else if(pos == stoerrelse){
      leggTil(x);
    } else {
      Node gjeldende = foran;

      for(int i = 0; i < pos; i++){
        gjeldende = gjeldende.neste;
      }

      Node foer = gjeldende.forrige;
      foer.neste = tmp;
      tmp.forrige = foer;
      tmp.neste = gjeldende;
      gjeldende.forrige = tmp;
      stoerrelse++;
    }
  }

  //skal sette inn et element på slutten av listen
  public void leggTil (T x){
    Node ny = new Node(x);

    if(erTom()){
      foran = ny;
    } else {
      sist.neste = ny;
      ny.forrige = sist;
    }
    sist = ny;
    stoerrelse++;
  }

  //skal sette inn elementet på en gitt posisjon og overskrive det som var der fra før av
  public void sett (int pos, T x){
    Node gjeldende = hentNode(pos);
    gjeldende.data = x;
  }

  //henter ut et element (uten å fjerne det fra lista) på oppgitt indeks (husk å telle fra indeks 0 og oppover)
  public T hent (int pos){
    Node denne = hentNode(pos);
    return denne.data;
  }

  public Node hentNode(int pos){

    if(pos < 0 || pos >= stoerrelse || erTom()){
      throw new UgyldigListeIndeks(pos);
    } else {
      Node gjeldende = foran;
      for(int i = 0; i < pos; i++){
        gjeldende = gjeldende.neste;
      }
      return gjeldende;
    }
  }

  //skal fjerne på gitt indeks i listen
  public T fjern (int pos){
    Node gjeldende = foran;

    if(pos < 0 || pos >= stoerrelse || erTom()){
      throw new UgyldigListeIndeks(pos);
    } else if (pos == 0){
      foran = foran.neste;
      if (foran != null){
        foran.forrige = null;
      }
      stoerrelse--;
    } else if (pos == stoerrelse-1){
      gjeldende = sist;
      sist = sist.forrige;
      sist.neste=null;
      stoerrelse--;
    } else {
      for(int i = 0; i < pos; i++){
        gjeldende = gjeldende.neste;
      }

      Node foer = gjeldende.forrige;
      Node etter = gjeldende.neste;
      foer.neste = gjeldende.neste;
      etter.forrige = foer;
      stoerrelse--;
    }
    return gjeldende.data;
  }

  //skal fjerne og returnere elementet på starten av listen
  public T fjern (){
    Node tmp = foran;

    if(erTom()){
      throw new UgyldigListeIndeks(0);
    }
    foran = foran.neste;
    if (foran != null){
      foran.forrige = null;
    }
    stoerrelse--;
    return tmp.data;
  }

  public void skrivListe(){
    Node temp = foran;
    System.out.println("-----");

    while(temp != null){
      System.out.println(temp.data);
      temp = temp.neste;
    }
    System.out.println();
  }

  public Iterator<T> iterator(){
    return new MinIterator();
  }

  private class MinIterator implements Iterator<T>{
    Node nodeNeste = foran;

    public boolean hasNext(){
      return nodeNeste!=null;
    }

    public T next(){
      T returData = nodeNeste.data;
      nodeNeste = nodeNeste.neste;
      return returData;
    }
  }
}
