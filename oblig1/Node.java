class Node{
    private int minne;
    private int antProsessor;

    public Node(int minne, int antProsessor){
        this.minne = minne;
        this.antProsessor = antProsessor;
    }

    public int hentMinne(){
        return minne;
    }

    public int hentAntProsessor(){
        return antProsessor;
    }
}
