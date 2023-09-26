// ENİS MÜCAHİD İSKENDER   2220356046

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DuckHunt extends Application {

    public static final double SCALE = 3; // to arrange scale , default  value  is : 3
    public static final double VOLUME =  0.025;  // to arrange volume of sounds, default  value  is : 0.025

    public static final String URL_BASE = "assets/";

    // MEDIA AND MEDIAPLAYER VARIABLES CREATED AS STATIC TO MAKE THEM REACHABLE AND UNIQUE
    public static Media gunSound;
    public static MediaPlayer gunSoundPlayer;

    public static Media winSound;
    public static MediaPlayer winSoundPlayer;

    public static Media gameCompletedSound;
    public static MediaPlayer gameCompletedSoundPlayer;

    public static Media gameOverSound;
    public static MediaPlayer gameOverSoundPlayer;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane rootPane = new Pane();
        welcomePane wp = new welcomePane();
        StackPane WelcomePane = wp.welcomePaneCreator();
        SelectionPane selectionPane = new SelectionPane();

        // LOADING SOUNDS
        Media titleSound = new Media(getClass().getResource(URL_BASE+"effects/Title.mp3").toExternalForm());
        MediaPlayer titleSoundPlayer = new MediaPlayer(titleSound);
        titleSoundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        titleSoundPlayer.play();
        titleSoundPlayer.setVolume(VOLUME);

        Media introSound = new Media(getClass().getResource(URL_BASE+"effects/Intro.mp3").toExternalForm());
        MediaPlayer introSoundPlayer = new MediaPlayer(introSound);
        introSoundPlayer.setVolume(VOLUME);

        gunSound = new Media(getClass().getResource(URL_BASE+"effects/GunShot.mp3").toExternalForm());
        gunSoundPlayer = new MediaPlayer(gunSound);
        gunSoundPlayer.setVolume(VOLUME);

        winSound = new Media(getClass().getResource(URL_BASE+"effects/LevelCompleted.mp3").toExternalForm());
        winSoundPlayer = new MediaPlayer(winSound);
        winSoundPlayer.setVolume(VOLUME);

        gameCompletedSound = new Media(getClass().getResource(URL_BASE+"effects/GameCompleted.mp3").toExternalForm());
        gameCompletedSoundPlayer = new MediaPlayer(gameCompletedSound);
        gameCompletedSoundPlayer.setVolume(VOLUME);

        gameOverSound = new Media(getClass().getResource(URL_BASE+"effects/GameOver.mp3").toExternalForm());
        gameOverSoundPlayer = new MediaPlayer(gameOverSound);
        gameOverSoundPlayer.setVolume(VOLUME);








        rootPane.getChildren().add(WelcomePane);
        Scene scene = new Scene(rootPane);


        scene.setOnKeyPressed(event -> {

            //WELCOME SCREEN KEYPRESS'S
            if(event.getCode() == KeyCode.ESCAPE && rootPane.getChildren().contains(WelcomePane)){
                primaryStage.close();
            } else if (event.getCode() == KeyCode.ENTER && rootPane.getChildren().contains(WelcomePane)) {
                rootPane.getChildren().clear();
                rootPane.getChildren().add(selectionPane);

            // SELECTION SCREEN KEYPRESS'S
            }else if (event.getCode() == KeyCode.ESCAPE && rootPane.getChildren().contains(selectionPane)) {
                rootPane.getChildren().clear();
                rootPane.getChildren().add(WelcomePane);
            } else if (event.getCode() == KeyCode.RIGHT && rootPane.getChildren().contains(selectionPane)) {
                selectionPane.setCurrentBackgroundView(selectionPane.getCurrentBackgroundView(),"RIGHT");
            } else if (event.getCode() == KeyCode.LEFT && rootPane.getChildren().contains(selectionPane)) {
                selectionPane.setCurrentBackgroundView(selectionPane.getCurrentBackgroundView(),"LEFT");
            }else if (event.getCode() == KeyCode.UP && rootPane.getChildren().contains(selectionPane)) {
                selectionPane.setCurrentCrosshairView(selectionPane.getCurrentCrosshairView(),"UP");
            }else if (event.getCode() == KeyCode.DOWN && rootPane.getChildren().contains(selectionPane)) {
                selectionPane.setCurrentCrosshairView(selectionPane.getCurrentCrosshairView(),"DOWN");
            } else if (event.getCode() == KeyCode.ENTER && rootPane.getChildren().contains(selectionPane)) {

                // game starts!!
                titleSoundPlayer.stop();
                introSoundPlayer.play();

                // with  "setOnEndOfMedia"  method I make program wait until the Intro music end to start the game.
                introSoundPlayer.setOnEndOfMedia(() -> {
                    introSoundPlayer.stop();
                    Level1 l1Pane = new Level1();
                    rootPane.getChildren().clear();
                    rootPane.getChildren().add(l1Pane);

                });

            } else if (event.getCode() == KeyCode.ENTER  && Level1.gameWin) {

                //Win sound stopped
                winSoundPlayer.stop();
                winSoundPlayer.seek(Duration.ZERO);

                // level 2 starts
                Level1.gameWin = false;
                Level1.gameEnd = false;
                Level2 l2Pane = new Level2();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l2Pane);

            } else if (event.getCode() == KeyCode.ESCAPE     && Level1.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                Level1.gameLose = false;
                Level1.gameEnd = false;
                // backs to Welcome Pane
                rootPane.getChildren().clear();
                rootPane.getChildren().add(WelcomePane);

            }else if (event.getCode() == KeyCode.ENTER     && Level1.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                // starts from level1
                Level1.gameLose = false;
                Level1.gameEnd = false;
                Level1 l1PaneStart = new Level1();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l1PaneStart);


            }else if (event.getCode() == KeyCode.ENTER  && Level2.gameWin) {

                //Win sound stopped
                winSoundPlayer.stop();
                winSoundPlayer.seek(Duration.ZERO);

                //level 3 starts
                Level2.gameWin = false;
                Level2.gameEnd = false;
                Level3 l3Pane = new Level3();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l3Pane);


            }else if (event.getCode() == KeyCode.ESCAPE     && Level2.gameLose) {
                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                Level2.gameLose = false;
                Level2.gameEnd = false;
                // backs to Welcome Pane
                rootPane.getChildren().clear();
                rootPane.getChildren().add(WelcomePane);

            }else if (event.getCode() == KeyCode.ENTER     && Level2.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                // starts from level1
                Level2.gameLose = false;
                Level2.gameEnd = false;
                Level1 l1PaneStart = new Level1();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l1PaneStart);


            }else if (event.getCode() == KeyCode.ENTER  && Level3.gameWin) {
                //Win sound stopped
                winSoundPlayer.stop();
                winSoundPlayer.seek(Duration.ZERO);

                //level 4 starts
                Level3.gameWin = false;
                Level3.gameEnd = false;
                Level4 l4Pane = new Level4();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l4Pane);


            }else if (event.getCode() == KeyCode.ESCAPE     && Level3.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                Level3.gameLose = false;
                Level3.gameEnd = false;
                // backs to Welcome Pane
                rootPane.getChildren().clear();
                rootPane.getChildren().add(WelcomePane);

            }else if (event.getCode() == KeyCode.ENTER     && Level3.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                // starts from level1
                Level3.gameLose = false;
                Level3.gameEnd = false;
                Level1 l1PaneStart = new Level1();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l1PaneStart);


            }else if (event.getCode() == KeyCode.ENTER  && Level4.gameWin) {

                //Win sound stopped
                winSoundPlayer.stop();
                winSoundPlayer.seek(Duration.ZERO);

                //level 5 starts
                Level4.gameWin = false;
                Level4.gameEnd = false;
                Level5 l5Pane = new Level5();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l5Pane);


            }else if (event.getCode() == KeyCode.ESCAPE     && Level4.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                Level4.gameLose = false;
                Level4.gameEnd = false;
                // backs to Welcome Pane
                rootPane.getChildren().clear();
                rootPane.getChildren().add(WelcomePane);

            }else if (event.getCode() == KeyCode.ENTER     && Level4.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                // starts from level1
                Level4.gameLose = false;
                Level4.gameEnd = false;
                Level1 l1PaneStart = new Level1();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l1PaneStart);


            }else if (event.getCode() == KeyCode.ENTER  && Level5.gameWin) {

                //Win sound stopped
                winSoundPlayer.stop();
                winSoundPlayer.seek(Duration.ZERO);

                //level 6 starts
                Level5.gameWin = false;
                Level5.gameEnd = false;
                Level6 l6Pane = new Level6();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l6Pane);


            }else if (event.getCode() == KeyCode.ESCAPE     && Level5.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                Level5.gameLose = false;
                Level5.gameEnd = false;
                // backs to Welcome Pane
                rootPane.getChildren().clear();
                rootPane.getChildren().add(WelcomePane);

            }else if (event.getCode() == KeyCode.ENTER     && Level5.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                // starts from level1
                Level5.gameLose = false;
                Level5.gameEnd = false;
                Level1 l1PaneStart = new Level1();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l1PaneStart);


            }else if (event.getCode() == KeyCode.ENTER  && Level6.gameWin) {

                // game Finished sound plays
                gameCompletedSoundPlayer.stop();
                gameCompletedSoundPlayer.seek(Duration.ZERO);

                //game Finished
                Level6.gameWin = false;
                Level6.gameEnd = false;
                Level1 l1Again = new Level1();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l1Again);

            }
            else if (event.getCode() == KeyCode.ESCAPE  && Level6.gameWin) {

                // game Finished sound plays
                gameCompletedSoundPlayer.stop();
                gameCompletedSoundPlayer.seek(Duration.ZERO);

                //game Finished
                Level6.gameWin = false;
                Level6.gameEnd = false;

                //goes back to welcome(title screen)
                rootPane.getChildren().clear();
                rootPane.getChildren().add(WelcomePane);


            }else if (event.getCode() == KeyCode.ESCAPE     && Level6.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                Level6.gameLose = false;
                Level6.gameEnd = false;
                // backs to Welcome Pane
                rootPane.getChildren().clear();
                rootPane.getChildren().add(WelcomePane);

            }else if (event.getCode() == KeyCode.ENTER     && Level6.gameLose) {

                //gameLose sound stopped
                gameOverSoundPlayer.stop();
                gameOverSoundPlayer.seek(Duration.ZERO);

                // starts from level1
                Level6.gameLose = false;
                Level6.gameEnd = false;
                Level1 l1PaneStart = new Level1();
                rootPane.getChildren().clear();
                rootPane.getChildren().add(l1PaneStart);


            }



        });


        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(URL_BASE+"favicon/1.png"));
        primaryStage.setTitle("DuckHunt");
        primaryStage.show();



    }








}
