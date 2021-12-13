package application;
import java.io.File;
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
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level2 extends Application{

    private ArrayList<Button> bList = new ArrayList<>();
    private Button[][] b = new Button[10][10];
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane mainPane = new BorderPane();
        BorderPane pane2 = new BorderPane();
        pane2.setStyle("-fx-border-color: red");
        mainPane.setStyle("-fx-border-color: red");
        mainPane.setTop(pane2);

/**SOUND EFFECT EVERY MOUSE CLICK*/
        String musicFile = "hll.mp3";
        Media effect = new Media(new File(musicFile).toURI().toString());
        MediaPlayer media = new MediaPlayer(effect);
//CREATING GRIDPANE FOR THE LEVEL
        GridPane gridPane2 = new GridPane();
        gridPane2.setAlignment(Pos.CENTER);
        gridPane2.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane2.setHgap(2.5);
        gridPane2.setVgap(2.5);

//TAKING INPUT FROM LEVELS FOLDER
        File file = new File("levels\\level2.txt");
        game.inputTake(file, b,bList,gridPane2);

//ADDING GRIDPANE TO THE MAINPANE
        mainPane.setCenter(gridPane2);

/**CLICK COUNT FIELD */
        TextField clickCount = new TextField();clickCount.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));clickCount.setEditable(false);
        clickCount.setAlignment(Pos.TOP_CENTER);clickCount.setText(Integer.toString(game.counter)+" CLICKS");

        /*INITIALIZING NEXT LEVEL BUTTON */	    	Button nextLevel = new Button("Next Level>>");
        nextLevel.setVisible(false);
//TEXTFIELDS HBOX
        TextField yourScore = new TextField();
        yourScore.setText(Integer.toString(game.getScore()));
        yourScore.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
        yourScore.setEditable(false); yourScore.setText("Score : "+Integer.toString(game.getYourScore()));

        TextField infoBox = new TextField();infoBox.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));infoBox.setEditable(false);infoBox.setText("Box Hit Info");


//CLICK ACTION
        for (int i = 1; i < 9; i++) {
            for(int j = 1; j < 9 ; j++ ) {
                Button b1=b[i][j];
                Button b2,b3,b4,b5;
                b2=b[i+1][j];  b3=b[i-1][j] ; b4=b[i][j+1] ; b5=b[i][j-1];
                /*IF RED BOX HIT */
                if(b1.getStyle()=="-fx-background-color: red") {
                    b1.setOnAction(e->{media.seek(Duration.ZERO);media.play(); game.countClick() ;
                        game.updateBlock(b1,b2,b3,b4,b5) ;
                        int b1r=GridPane.getRowIndex(b1);int b1c=GridPane.getColumnIndex(b1);
                        /*INFO BOX*/
                        infoBox.setText("Box:"+String.valueOf(b1r)+"-"+String.valueOf(b1c)+"-Hit");
                        clickCount.setText(Integer.toString(game.counter)+" CLICKS");
                        yourScore.setText("Score : "+Integer.toString(game.getYourScore()));
                        if(game.getIsNext()==45) {nextLevel.setVisible(true);}});
                }
                /*IF BLUE BOX HIT */
                if(b1.getStyle()=="-fx-background-color: blue") {
                    b1.setOnAction(e->{media.seek(Duration.ZERO);media.play(); game.countClick() ;
                        game.updateBlock(b1,b2,b3,b4,b5) ;
                        int b1r=GridPane.getRowIndex(b1);int b1c=GridPane.getColumnIndex(b1);
                        /*INFO BOX*/
                        infoBox.setText("Box:"+String.valueOf(b1r)+"-"+String.valueOf(b1c)+"-Hit");
                        clickCount.setText(Integer.toString(game.counter)+" CLICKS");
                        yourScore.setText("Score : "+Integer.toString(game.getYourScore()));
                        if(game.getIsNext()==45) { nextLevel.setVisible(true);}});
                }
            }
        }

/**BUTTONS HBOX	*/
        HBox hBox = new HBox(30);
        /** PAINTING GREY OR BLACK BACKGROUND IN VBOX	*/
        VBox vBox = new VBox(10);
        vBox.setStyle("-fx-background-color: white");
        Button grey = new Button("GREY");grey.setStyle("-fx-background-color: grey");	grey.setOnAction(e ->{System.out.println("painted grey");});grey.setPrefSize(50, 5);grey.setFont(Font.font("Courier", FontWeight.BOLD,  9));grey.setTextFill(Color.WHITE);
        Button black = new Button("BLACK");black.setStyle("-fx-background-color: black");	black.setOnAction(e ->{ System.out.println("painted black");});black.setPrefSize(50, 5);black.setFont(Font.font("Courier", FontWeight.BOLD, 9));black.setTextFill(Color.WHITE);
        vBox.getChildren().addAll(grey,black);
        grey.setOnAction(e->{ for(Button button: bList) { if(button.getStyle()=="-fx-background-color: black") {button.setStyle("-fx-background-color: grey");}}});
        black.setOnAction(e->{ for(Button button: bList) { if(button.getStyle()=="-fx-background-color: grey") {button.setStyle("-fx-background-color: black");}}});
//BUTTONS
        hBox.setPadding(new Insets(10, 15, 15, 105));
        hBox.setStyle("-fx-background-color: lightgreen");
        Button scrnsht = new Button("SCREENSHOT");scrnsht.setStyle("-fx-background-color: white");	scrnsht.setOnAction(e ->System.out.println("SCREENSHOT TAKEN"));
        Button exit = new Button("EXIT");exit.setStyle("-fx-background-color: red");	exit.setOnAction(e ->{System.out.println("Exit Clicked"); System.exit(0);});
        Button restart = new Button("NEW GAME");restart.setStyle("-fx-background-color: white");
        restart.setOnAction(e-> {game scene = new game();try {scene.start(primaryStage);game.countSet();game.setScore(0);game.setIsNext(0);} catch (Exception e1) {e1.printStackTrace();}});

        hBox.getChildren().addAll(scrnsht,exit,restart,vBox);
        pane2.setTop(hBox);

/**TEXTFIELDS HBOX	*/
        HBox hBox1 = new HBox(25);
        hBox1.setPadding(new Insets(50));
        TextField level = new TextField();level.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));	level.setEditable(false);   level.setText("Level #2");
        level.setAlignment(Pos.TOP_LEFT);
        hBox1.setPadding(new Insets(10, 50, 10, 80));
        hBox1.setStyle("-fx-background-color: lightblue");
        hBox1.getChildren().addAll(level,clickCount,yourScore);
        pane2.setBottom(hBox1);

/** BOTTOM BOX EVENT*/
        HBox hBox2 = new HBox(390);
        hBox2.setPadding(new Insets(2, 10, 2, 10));
        infoBox.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
        hBox2.getChildren().add(infoBox);
        hBox2.getChildren().add(nextLevel);
        mainPane.setBottom(hBox2);nextLevel.setAlignment(Pos.BOTTOM_RIGHT);infoBox.setAlignment(Pos.BOTTOM_LEFT);

        Scene scene2 = new Scene(mainPane,700,700);
        primaryStage.setScene(scene2);
        primaryStage.show();

/**PASSING TO LEVEL3 */
        nextLevel.setOnAction(e->
        {Level3 scene3 = new Level3();try {scene3.start(primaryStage);} catch (Exception e1) {e1.printStackTrace();}});

/**SCREENSHOT LAMBDA ACTION :	*/
        scrnsht.setOnAction(e->
        {try {game.scrnsht(scene2);infoBox.setText("SCREENSHOT TAKEN"); } catch (Exception e1) {e1.printStackTrace();}});
    }
}

