
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BrickGame extends Application {

    static int counter = 0;					//mouse click counter
    private static int countShot = 1;		//when taking screenshot we increment this and every screenshot gets different name.
    private static int score=0;				//showing score
    private static int yourScore=0;         //The score user gets
    private static int scoreCut=0;			//score regulation in case of 4 or 5 buttons affected by clicking
    private static int isNext=0; 			/**this argument is to test some properties for game developers*/

    @Override
    public void start(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        BorderPane pane2 = new BorderPane();
        pane2.setStyle("-fx-border-color: red");
        mainPane.setStyle("-fx-border-color: red");
        mainPane.setTop(pane2);
        mainPane.setPrefHeight(30);mainPane.setPrefWidth(250);mainPane.setStyle("-fx-background-color: black");

/**SOUND EFFECT EVERY MOUSE CLICK*/
        String musicFile = "hll.mp3";     // For example
        Media effect = new Media(new File(musicFile).toURI().toString());
        MediaPlayer media = new MediaPlayer(effect);

/**CLICK COUNT FIELD*/
        TextField clickCount = new TextField();clickCount.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));clickCount.setEditable(false);
        clickCount.setAlignment(Pos.TOP_CENTER);clickCount.setText("0");

/**BUTTONS UPPER HBOX	*/
        HBox hBox = new HBox(30);
        hBox.setPadding(new Insets(10, 15, 15, 160));
        hBox.setStyle("-fx-background-color: lightgreen");
        Button scrnsht = new Button("SCREENSHOT");scrnsht.setStyle("-fx-background-color: white");	scrnsht.setOnAction(e ->{media.seek(Duration.ZERO);media.play();System.out.println("SCREENSHOT TAKEN");});
        Button exit = new Button("EXIT");exit.setStyle("-fx-background-color: red");	exit.setOnAction(e ->{System.out.println("Exit Clicked"); System.exit(0);});

        hBox.getChildren().addAll(scrnsht,exit);
        pane2.setTop(hBox);
        /*TEXTFIELDS UPPER HBOX	*/
        HBox hBox1 = new HBox(14);
        TextField welcome = new TextField();welcome.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));	welcome.setEditable(false); welcome.setText("WELCOME! TO THE BEST BRICK GAME...");
        welcome.setAlignment(Pos.TOP_CENTER);
        welcome.setPrefWidth(300);
        hBox1.setPadding(new Insets(2, 50, 2, 157));
        hBox1.setStyle("-fx-background-color: lightblue");
        hBox1.getChildren().add(welcome);
        pane2.setBottom(hBox1);

        /*BOTTOM HBOX */
        HBox hBox2 = new HBox(390);
        hBox2.setPadding(new Insets(2, 10, 2, 10));
        TextField infoBox = new TextField();infoBox.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));infoBox.setEditable(false);
        infoBox.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
        hBox2.getChildren().add(infoBox);
        /*START BUTTON */
        Button start = new Button("START GAME");
        start.setPrefHeight(30);start.setPrefWidth(400);start.setStyle("-fx-alignment: center;-fx-border-color: black;-fx-background-color: yellow;-fx-font-weight: bold;-fx-font-size: 5em;");
        mainPane.setCenter(start);
        mainPane.setBottom(hBox2);start.setAlignment(Pos.BOTTOM_RIGHT);infoBox.setAlignment(Pos.BOTTOM_LEFT);
        Scene scene = new Scene(mainPane, 700, 700);scene.setFill(Color.BLACK);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        /*STARTING GAME */
        start.setOnAction(e-> {Level1 scene1 = new Level1();try {scene1.start(primaryStage);countSet();score=0;} catch (Exception e1) {e1.printStackTrace();}});

        /*SCREENSHOT LAMBDA ACTION */
        scrnsht.setOnAction(e-> {try {scrnsht(scene);infoBox.setText("SCREENSHOT TAKEN"); } catch (Exception e1) {e1.printStackTrace();}});
    }

    /*SCREENSHOT  METHOD */
    public static void scrnsht(Scene scene) throws Exception{
        WritableImage image = new WritableImage((int)scene.getWidth(), (int)scene.getHeight());
        scene.snapshot(image);
        File file = new File("screenshot_"+countShot+".png");
        countShot++;
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        System.out.println("SCREENSHOT SAVE TO : " + file.getAbsolutePath());
    }
    //MAIN METHOD
    public static void main(String[] args) {
        launch(args);
    }
    //CLICK COUNT METHOD
    public static void countClick() {
        counter++;
    }
    //SET COUNT CLICK TO ZERO AT THE BEGINNING OF THE GAME
    public static void countSet() {
        counter=0;
    }
    // SET SCORE TO ZERO AT THE BEGINNING OF THE GAME
    public static void scoreSet() {
        yourScore=0;
    }

    //UPDATE BLOCK COLOR METHOD
    public static void updateBlock (Button b1,Button b2,Button b3,Button b4,Button b5) {

        if(b1.getStyle()=="-fx-background-color: red") {
            b1.setStyle("-fx-background-color: blue");
            yourScore=yourScore-3 ; scoreCut++ ; isNext++;
        }
        else if(b1.getStyle()=="-fx-background-color: blue") {
            b1.setStyle("-fx-background-color: white");
            yourScore=yourScore-3 ; scoreCut++ ; isNext++;
        }

        if(b2.getStyle()=="-fx-background-color: red") {
            b2.setStyle("-fx-background-color: blue");
            yourScore=yourScore+2 ; scoreCut++ ; isNext++;
        }
        else if(b2.getStyle()=="-fx-background-color: blue") {
            b2.setStyle("-fx-background-color: white");
            yourScore=yourScore+2 ; scoreCut++ ; isNext++;
        }
        if(b3.getStyle()=="-fx-background-color: red") {
            b3.setStyle("-fx-background-color: blue");
            yourScore=yourScore+2 ; scoreCut++ ; isNext++;
        }
        else if(b3.getStyle()=="-fx-background-color: blue") {
            b3.setStyle("-fx-background-color: white");
            yourScore=yourScore+2;scoreCut++;isNext++;
        }
        if(b4.getStyle()=="-fx-background-color: red") {
            b4.setStyle("-fx-background-color: blue");
            yourScore=yourScore+2;scoreCut++;isNext++;
        }
        else if(b4.getStyle()=="-fx-background-color: blue") {
            b4.setStyle("-fx-background-color: white");
            yourScore=yourScore+2;scoreCut++;isNext++;
        }
        if(b5.getStyle()=="-fx-background-color: red") {
            b5.setStyle("-fx-background-color: blue");
            yourScore=yourScore+2;scoreCut++;isNext++;
        }
        else if(b5.getStyle()=="-fx-background-color: blue") {
            b5.setStyle("-fx-background-color: white");
            yourScore=yourScore+2;scoreCut++;isNext++;
        }
//GAME RULE REGULATION DECREMENT THE SCORE BY ONE IF 4 OR 5 BOX HIT
        if(scoreCut%4==0||scoreCut%5==0) { yourScore--;}
        scoreCut=0;
    }

    //INPUT TAKING METHOD
    public static void inputTake(File file,Button[][] b,ArrayList<Button> bList,
                                 GridPane gridPane1) throws FileNotFoundException {
        //CREATING AND ADDING ALL BUTTONS IN AN ARRAY AND AN ARRAYLIST
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10 ; j++ ) {
                Button button = new Button();
                button.setPrefSize(80, 80);
                button.setStyle("-fx-background-color: grey");
                b[i][j]=button;	bList.add(button);
                gridPane1.add(button,i,j);
            }
        }
        //READING FROM THE FILE
        try( Scanner input = new Scanner(file)){
            while (input.hasNext()) {
                String line = input.nextLine();
                String [] l1 = line.split(",");


                if(l1[0].contentEquals("Wood")) {
                    b[Integer.parseInt(l1[2])][Integer.parseInt(l1[1])].setStyle("-fx-background-color: red");

                }
                if(l1[0].contentEquals("Mirror")) {
                    b[Integer.parseInt(l1[2])][Integer.parseInt(l1[1])].setStyle("-fx-background-color: blue");

                }
                if(l1[0].contentEquals("Empty")) {
                    b[Integer.parseInt(l1[2])][Integer.parseInt(l1[1])].setStyle("-fx-background-color: white");

                }
            }
        }
    }
    //GETTERS AND SETTERS
    public static int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        game.counter = counter;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        game.score = score;
    }

    public static int getIsNext() {
        return isNext;
    }

    public static void setIsNext(int isNext) {
        game.isNext = isNext;
    }

    public static int getYourScore() {
        return yourScore;
    }
    public static void setYourScore(int yourScore) {
        game.yourScore = yourScore;
    }
}
}
