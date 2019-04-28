import javafx.stage.FileChooser; //Viktig
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.GridPane; //Viktig
import java.io.FileNotFoundException;

public class LabyrintLoeser extends Application {
    private LabRute[][] ruter;
    private Stage primaryStage;
    private Scene labyrintScene;
    private FileChooser filvelger;
    private Label score; //viser antall loesninger
    private Button filknapp; //knapp for aa velge fil
    public static BorderPane borderPane;
    public static Labyrint guiLabyrint;
    public static GridPane guiGrid;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Labyrinth Solver");
        lagLabyrintScene();
        primaryStage.setScene(labyrintScene);
        primaryStage.show();
    }

    private void lagLabyrintScene() {
        BorderPane rootPane = new BorderPane();
        borderPane = rootPane;
        GridPane labPane = new GridPane();

        filvelger = new FileChooser();
        filknapp = new Button("Velg labyrintfil");
        rootPane.setCenter(filknapp);

        filknapp.setOnAction(
        new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                File labyrintFile = filvelger.showOpenDialog(primaryStage); //aapner filvelger
                try {
                    guiLabyrint = Labyrint.lesFraFil(labyrintFile); //leser filen med oblig 5 labyrintklassen
                    System.out.println(guiLabyrint.toString());

                    guiGrid = new GridPane(); //grid for labyrintrutene
                    int storrelse = 20; //storrelsen paa ruten
                    ruter = new LabRute[guiLabyrint.lengde][guiLabyrint.hoyde]; //lager et rutenett for labyrintrutene

                    for(int x = 0; x < guiLabyrint.lengde; x++) {
                        for(int y = 0; y < guiLabyrint.hoyde; y++) {
                            ruter[x][y] = new LabRute(storrelse, guiLabyrint.hentRute(x, y)); //lager GUIrutene
                            guiGrid.add(ruter[x][y], x, y); //legger til rutene i grid'en
                        }
                    }
                } catch (FileNotFoundException ex) {}
                rootPane.setCenter(guiGrid);
                primaryStage.show();
            }
        });
        labyrintScene = new Scene(rootPane);
    }

    private class LabRute extends Pane {
        public boolean erHvit = true; //sjekker om ruten er hvit eller ikke.
        Rute rute; //enhver LabRute vet hvilken rute den er i labyrinten

        public LabRute(int storrelse, Rute rute) {
            this.rute = rute;
            if(rute.tilTegn() == '#') erHvit = false;
            oppdaterFarge();
            setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
            null, new BorderWidths(1))));
            setMinWidth(storrelse);
            setMinHeight(storrelse);

            addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { //naar en hvit rute klikkes paa...
                public void handle(MouseEvent event) {
                    if (erHvit) {
                    Lenkeliste<String> loesninger = guiLabyrint.finnUtveiFra(rute.x, rute.y); //hent alle loesninger fra den ruten
                    String loesning = loesninger.hent(); //hent en loesning fra Lenkelisteen med loesninger

                    if (loesning == null) {
                        score = new Label("Ingen løsninger funnet!");

                        for (int x = 0; x < guiLabyrint.lengde; x++) {
                            for (int y = 0; y < guiLabyrint.hoyde; y++) {
                                if (ruter[x][y].erHvit) {
                                    ruter[x][y].settFarge(Color.WHITE);
                                }
                            }
                        }
                        borderPane.setTop(score);
                        primaryStage.show();

                    } else {
                        boolean[][] guiLoesning = losningStringTilTabell(loesning, guiLabyrint.lengde, guiLabyrint.hoyde); //konverter loesningen

                        for (int x = 0; x < guiLabyrint.lengde; x++) {
                            for (int y = 0; y < guiLabyrint.hoyde; y++) { //loep gjennom labyrintens ruter
                                if (guiLoesning[x][y]) //hvis ruten er en del av loesningen...
                                    ruter[x][y].settFarge(Color.YELLOW); //farg den GUL
                                else if(ruter[x][y].erHvit) { //hvis det er en hvit rute, men den ikke er en del av losningen...
                                    ruter[x][y].settFarge(Color.WHITE); //behold hvitfargen
                                }
                            }
                        }
                        score = new Label("Antall løsninger funnet: " + guiLabyrint.liste.stoerrelse());
                        borderPane.setTop(score);
                        primaryStage.show();
                        }
                    }
                }
            });
        }

        public void oppdaterFarge() {
            if(erHvit) { //setter fargen til aa vaere hvit hvis ruten er hvit
                setBackground(new Background(
                new BackgroundFill(Color.WHITE, null, null)));
            } else { //ellers saa settes fargen til svart.
                setBackground(new Background(
                new BackgroundFill(Color.BLACK, null, null)));
            }
        }

        public void settFarge(Color color) {
            setBackground(new Background(
            new BackgroundFill(color, null, null))); //farger ruten i den fargen vi har satt den til
        }

        @Override
        public String toString() {
            return erHvit ? " " : "#";
        }
    }

    /**
    * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
    * av losningstien.
    * @param losningString String-representasjon av utveien
    * @param bredde        bredde til labyrinten
    * @param hoyde         hoyde til labyrinten
    * @return              2D-representasjon av rutene der true indikerer at
    *                      ruten er en del av utveien.
    */
    static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
        boolean[][] losning = new boolean[bredde][hoyde];
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
        java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
        while(m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            losning[x][y] = true;
        }
        return losning;
    }
}
