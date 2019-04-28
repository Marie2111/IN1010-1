import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Regneklynge{
    private int antallNoderPerRack;
    private String filnavn;
    private ArrayList<Rack> r = new ArrayList<>();
    private ArrayList<String> linjer = new ArrayList<>();

    public Regneklynge(String filnavn){
        this.filnavn = filnavn;
        lesDataFraFil(filnavn);
    }

    public Regneklynge(int antallNoderPerRack){
        this.antallNoderPerRack=antallNoderPerRack;
    }

    public void lesDataFraFil(String filnavn){
        File f = new File(filnavn);
        Scanner filleser = null;

        try{
            filleser = new Scanner(f);
            //System.out.println("Leser fil");
        } catch(FileNotFoundException e){
            System.out.println("Filen finnes ikke");
        }

        String linje = "";
        while(filleser.hasNextLine()){
            linjer.add(filleser.nextLine());
        }
        String antallNoderPerRack1 = linjer.get(0);
        antallNoderPerRack = Integer.parseInt(antallNoderPerRack1);
        System.out.println("antallNoderPerRack" + " " + antallNoderPerRack);

        for(int i=1; i<linjer.size(); i++){
            String[] linjeSplit=linjer.get(i).split("\\s+");
            int antallNoder = Integer.parseInt(linjeSplit[0]);
            int minne = Integer.parseInt(linjeSplit[1]);
            int antProsessor = Integer.parseInt(linjeSplit[2]);
            settInnNode2(antallNoder, minne, antProsessor);
        }
        return;
    }

    /*hjelpemetode*/
    public void settInnNode2(int antallNoder, int minne, int antProsessor){
        int i=0;

        while(i < antallNoder){
            Node node = new Node(minne, antProsessor);
            settInnNode(node);
            i++;
        }
    }

    public int antallRack(){
        return r.size();
    }

    /*setter inn noden inn et ledig Rack-objekt fra listen hvis det er plass.
    Hvis alle rackene er fulle, skal det lage et nytt Rack-objekt*/
    public void settInnNode(Node node){
        for(Rack rack: r){
            if(!rack.erFull()){
                rack.addNode(node);
                return;
            }
        }

        Rack rack = new Rack(antallNoderPerRack);
        rack.addNode(node);
        r.add(rack);
    }

    public int antProsessor(){
        int antProsessor=0;

        for(int i=0; i<r.size(); i++){
            antProsessor += r.get(i).antProsessor();
        }
        return antProsessor;
    }

    public int noderMedNokMinne(int paakrevdMinne){
        int antallNoder=0;

        for(int i=0; i<r.size(); i++){
            antallNoder += r.get(i).noderMedNokMinne(paakrevdMinne);
        }
        return antallNoder;
    }
}
