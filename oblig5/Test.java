import java.io.File;
import java.util.Iterator;

class Test {
  public static void main(String[] args) throws Exception {

    File fil = new File("7.in");
    Labyrint l =  Labyrint.lesFraFil(fil);
    System.out.println(l.toString());

    l.finnUtveiFra(3, 3);
    for(String losning : l.liste) {
      System.out.println(losning);
    }
    System.out.println();

  }
}
