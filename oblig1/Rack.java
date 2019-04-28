//Skal lagre Node-objektene i et array.Vi skal kunne til legge til noder i racket.
class Rack{
    private Node [] noder;
    private int antallNoder=0;
    private int plasser;

    public Rack (int plasser){
        noder=new Node[plasser];
    }

    public void addNode(Node node){
        noder[antallNoder]=node;
        antallNoder++;
    }

    public boolean erFull(){
        return antallNoder == noder.length;
    }

    public int antProsessor(){
        int antProsessor = 0;

        for(Node node: noder){
            if(node!= null){
              antProsessor += node.hentAntProsessor();
            }
        }
        return antProsessor;
    }

    //returnerer antall noder med minst paakrevende minne
    public int noderMedNokMinne(int paakrevdMinne){
        int antallNoder = 0;

        for(Node node: noder){
            if(node != null && node.hentMinne()>=paakrevdMinne){
              antallNoder+=1;
            }
        }
        return antallNoder;
    }
}
