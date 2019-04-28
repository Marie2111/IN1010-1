class Test{
    public static void main(String[] args){
        Regneklynge abel= new Regneklynge("regneklynge4.txt");

        /*for(int i=0; i<650; i++){
            abel.settInnNode(new Node(64, 1));
        }

        for(int i=0; i<16; i++){
            abel.settInnNode(new Node(1024, 2));
        }

        abel.lesDataFraFil("regneklynge.txt");*/

        System.out.println("Noder med minst 16 GB: " + abel.noderMedNokMinne(32));
        System.out.println("Noder med minst 64 GB: " + abel.noderMedNokMinne(64));
        System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));
        System.out.println("Antall prosessorer: " + abel.antProsessor());
        System.out.println("Antall rack: " + abel.antallRack());
    }
}
