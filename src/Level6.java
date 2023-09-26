import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Level6 extends Pane {
    private double BIRD_WIDTH = 80 * DuckHunt.SCALE / 3;
    private  double BIRD_HEIGHT = 60 * DuckHunt.SCALE / 3;
    private  double birdSpeed1X = 50 * DuckHunt.SCALE / 3;
    private  double birdSpeed1Y = -50 * DuckHunt.SCALE / 3;
    private  double birdSpeed2X = 50 * DuckHunt.SCALE / 3;
    private  double birdSpeed2Y = -60 * DuckHunt.SCALE / 3;

    private  double birdSpeed3X = 60 * DuckHunt.SCALE / 3;
    private  double birdSpeed3Y = -60 * DuckHunt.SCALE / 3;


    private  int numberOfAmmo = 9;
    private int numberOfBird = 3;

    public static boolean gameWin = false;
    public static boolean gameLose = false;
    public static boolean gameEnd = false;


    protected ImageView currentForegroundView;
    protected ImageView currentBackgroundView;

    protected ImageView cursorImageView;
    private ImageView birdView1;
    private ImageView birdView2;
    private ImageView birdView3;

    private int currentFrameIndex1 = 0;
    private int currentFrameIndex2 = 0;
    private int currentFrameIndex3 = 0;
    private Image[] firstBirdCrossedImages = new Image[3];
    private Image[] secondBirdCrossedImages = new Image[3];
    private Image[] thirdBirdCrossedImages = new Image[3];
    private Image[] birdFallImages = new Image[2];

    public Level6() {


        // getting current views
        SelectionPane sp = new SelectionPane();
        currentBackgroundView = sp.getCurrentBackgroundView();
        cursorImageView = sp.getCurrentCrosshairView();
        currentForegroundView = sp.getCurrentForegroundView();
        // bird images for horizontal animation and falling animation
        firstBirdCrossedImages[0] = new Image("assets/duck_blue/1.png");
        firstBirdCrossedImages[1] = new Image("assets/duck_blue/2.png");
        firstBirdCrossedImages[2] = new Image("assets/duck_blue/3.png");

        secondBirdCrossedImages[0] = new Image("assets/duck_red/1.png");
        secondBirdCrossedImages[1] = new Image("assets/duck_red/2.png");
        secondBirdCrossedImages[2] = new Image("assets/duck_red/3.png");

        thirdBirdCrossedImages[0] = new Image("assets/duck_black/1.png");
        thirdBirdCrossedImages[1] = new Image("assets/duck_black/2.png");
        thirdBirdCrossedImages[2] = new Image("assets/duck_black/3.png");

        birdFallImages[0] = new Image("assets/duck_red/7.png");
        birdFallImages[1] = new Image("assets/duck_red/8.png");

        birdView1 = new ImageView(firstBirdCrossedImages[0]);
        birdView1.setFitWidth(BIRD_WIDTH);
        birdView1.setFitHeight(BIRD_HEIGHT);
        birdView1.setLayoutY(200 * DuckHunt.SCALE / 3);
        birdView1.setLayoutX(300 * DuckHunt.SCALE / 3);

        birdView2 = new ImageView(secondBirdCrossedImages[0]);
        birdView2.setFitWidth(BIRD_WIDTH);
        birdView2.setFitHeight(BIRD_HEIGHT);
        birdView2.setLayoutY(70 * DuckHunt.SCALE / 3);
        birdView2.setLayoutX(60 * DuckHunt.SCALE / 3);


        birdView3 = new ImageView(thirdBirdCrossedImages[0]);
        birdView3.setFitWidth(BIRD_WIDTH);
        birdView3.setFitHeight(BIRD_HEIGHT);
        birdView3.setLayoutX(30 * DuckHunt.SCALE / 3);
        birdView3.setLayoutY(300 * DuckHunt.SCALE / 3);


        //creating text
        Text level = new Text("Level 6/6");
        level.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25 * DuckHunt.SCALE / 3));
        level.setFill(Color.DARKORANGE);
        Text numOfAmmo = new Text("Ammo Left: " + String.valueOf(numberOfAmmo));
        numOfAmmo.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25 * DuckHunt.SCALE / 3));
        numOfAmmo.setFill(Color.DARKORANGE);

        HBox hBoxForTexts = new HBox(170 * DuckHunt.SCALE / 3);
        hBoxForTexts.getChildren().addAll(level, numOfAmmo);
        hBoxForTexts.setAlignment(javafx.geometry.Pos.CENTER);
        hBoxForTexts.setPadding(new Insets(0, 0, 0, 325 * DuckHunt.SCALE / 3));


        cursorImageView.setFitWidth(35 * DuckHunt.SCALE / 3); // Set the width of the cursor image
        cursorImageView.setFitHeight(35 * DuckHunt.SCALE / 3); // Set the width of the cursor image
        //cursorImageView.setPreserveRatio(true); // Maintain the aspect ratio of the image


        // Set custom cursor
        setOnMouseMoved(event -> {
            cursorImageView.setTranslateX(event.getX() - cursorImageView.getBoundsInLocal().getWidth() / 2);
            cursorImageView.setTranslateY(event.getY() - cursorImageView.getBoundsInLocal().getHeight() / 2);
        });
        setCursor(javafx.scene.Cursor.NONE);

        // add whole to to pane
        getChildren().addAll(currentBackgroundView, birdView1,birdView2,birdView3, currentForegroundView, hBoxForTexts, cursorImageView);


        // Implement shooting functionality
        setOnMouseClicked(event -> {


            if (event.getButton() == MouseButton.PRIMARY && (!gameEnd)) {

                // gunshot sound
                DuckHunt.gunSoundPlayer.play();
                DuckHunt.gunSoundPlayer.seek(Duration.ZERO);

                numberOfAmmo--;
                numOfAmmo.setText("Ammo Left: " + String.valueOf(numberOfAmmo));
                double mouseX = event.getX();
                double mouseY = event.getY();


                // Understanding how the birds' hitten
                if (birdView1.getBoundsInParent().contains(mouseX, mouseY)) {
                    birdView1.setImage(birdFallImages[0]);
                    getChildren().remove(birdView1);
                    numberOfBird--;
                }
                if (birdView2.getBoundsInParent().contains(mouseX, mouseY)) {
                    birdView2.setImage(birdFallImages[0]);
                    getChildren().remove(birdView2);
                    numberOfBird--;
                }if (birdView3.getBoundsInParent().contains(mouseX, mouseY)) {
                    birdView3.setImage(birdFallImages[0]);
                    getChildren().remove(birdView3);
                    numberOfBird--;
                }





                if ((numberOfBird == 0) && (numberOfAmmo >= 0)) {

                    // win
                    // creating two text


                    Text youWinText = new Text("You have completed the game!");
                    youWinText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 45 * DuckHunt.SCALE / 3));
                    youWinText.setFill(Color.DARKORANGE);
                    Text pressEnterText = new Text("Press ENTER to play again\n      Press Esc to Exit");
                    pressEnterText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 45 * DuckHunt.SCALE / 3));
                    pressEnterText.setFill(Color.DARKORANGE);
                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(youWinText, pressEnterText);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setPadding(new Insets(200 * DuckHunt.SCALE / 3, 0, 0, 70 * DuckHunt.SCALE / 3));
                    getChildren().add(vBox);

                    // game completed sound plays
                    DuckHunt.gameCompletedSoundPlayer.play();


                    //animation for text
                    KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), event2 -> pressEnterText.setVisible(false));
                    KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), event2 -> pressEnterText.setVisible(true));
                    Timeline timeline1 = new Timeline(keyFrame1, keyFrame2);
                    timeline1.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
                    timeline1.play();
                    gameWin = true;
                    gameEnd = true;


                } else if ((!(numberOfBird == 0)) && (numberOfAmmo == 0)) {
                    // lose

                    // creating two text
                    Text youLoseText = new Text("GAME OVER!");
                    youLoseText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 80 * DuckHunt.SCALE / 3));
                    youLoseText.setFill(Color.DARKORANGE);
                    Text pressEnterEscText = new Text("Press ENTER to play again\n        Press ESC to exit");
                    pressEnterEscText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 45 * DuckHunt.SCALE / 3));
                    pressEnterEscText.setFill(Color.DARKORANGE);
                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(youLoseText, pressEnterEscText);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setPadding(new Insets(200 * DuckHunt.SCALE / 3, 0, 0, 95 * DuckHunt.SCALE / 3));
                    getChildren().add(vBox);


                    // game over sound plays
                    DuckHunt.gameOverSoundPlayer.play();


                    //animation for text
                    KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), event1 -> pressEnterEscText.setVisible(false));
                    KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), event1 -> pressEnterEscText.setVisible(true));
                    Timeline timeline2 = new Timeline(keyFrame1, keyFrame2);
                    timeline2.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
                    timeline2.play();
                    gameLose = true;
                    gameEnd = true;

                }


            }


        });


        // Create a Timeline to animate the bird
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> animateBird()));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }




    /**
     * This function created for animation of bird first it updates bird or bird's position with speed and updates bird or bird's frames
     * to make animation look like bird is flying.Lastly, I check boundaries to make it reverse from the border's of the pane.
     */
    private void animateBird() {

        // Update bird position
        //first bird
        birdView1.setLayoutX(birdView1.getLayoutX() + birdSpeed1X);
        birdView1.setLayoutY(birdView1.getLayoutY() + birdSpeed1Y);
        //second bird
        birdView2.setLayoutX(birdView2.getLayoutX() + birdSpeed2X);
        birdView2.setLayoutY(birdView2.getLayoutY() + birdSpeed2Y);
        //third bird
        birdView3.setLayoutX(birdView3.getLayoutX() + birdSpeed3X);
        birdView3.setLayoutY(birdView3.getLayoutY() + birdSpeed3Y);

        // Update bird frame
        currentFrameIndex1 = (currentFrameIndex1 + 1) % firstBirdCrossedImages.length;
        birdView1.setImage(firstBirdCrossedImages[currentFrameIndex1]);
        birdView1.setImage(firstBirdCrossedImages[currentFrameIndex2]);

        currentFrameIndex2 = (currentFrameIndex2 + 1) % secondBirdCrossedImages.length;
        birdView2.setImage(secondBirdCrossedImages[currentFrameIndex2]);
        birdView2.setImage(secondBirdCrossedImages[currentFrameIndex2]);

        currentFrameIndex3 = (currentFrameIndex3 + 1) % secondBirdCrossedImages.length;
        birdView3.setImage(thirdBirdCrossedImages[currentFrameIndex3]);
        birdView3.setImage(thirdBirdCrossedImages[currentFrameIndex3]);





        // Reverse direction if birds hits the border
        if (birdView1.getLayoutX() <= 0|| birdView1.getLayoutX() >= getWidth() - BIRD_WIDTH) {
            birdSpeed1X = -birdSpeed1X;
            birdView1.setScaleX(birdView1.getScaleX() * -1); // Flip bird horizontally
        }

        if(birdView1.getLayoutY() <= 0 || birdView1.getLayoutY() >= getHeight() - BIRD_HEIGHT){
            birdSpeed1Y = -birdSpeed1Y;
            birdView1.setScaleY(birdView1.getScaleY() * -1); // Flip bird vertically
        }

        // doing same thing for second bird.

        if (birdView2.getLayoutX() <= 0|| birdView2.getLayoutX() >= getWidth() - BIRD_WIDTH) {
            birdSpeed2X = -birdSpeed2X;
            birdView2.setScaleX(birdView2.getScaleX() * -1); // Flip bird horizontally
        }

        if(birdView2.getLayoutY() <= 0 || birdView2.getLayoutY() >= getHeight() - BIRD_HEIGHT){
            birdSpeed2Y = -birdSpeed2Y;
            birdView2.setScaleY(birdView2.getScaleY() * -1); // Flip bird vertically
        }

        // and for third bird too .
        if (birdView3.getLayoutX() <= 0|| birdView3.getLayoutX() >= getWidth() - BIRD_WIDTH) {
            birdSpeed3X = -birdSpeed3X;
            birdView3.setScaleX(birdView3.getScaleX() * -1); // Flip bird horizontally
        }

        if(birdView3.getLayoutY() <= 0 || birdView3.getLayoutY() >= getHeight() - BIRD_HEIGHT){
            birdSpeed3Y = -birdSpeed3Y;
            birdView3.setScaleY(birdView3.getScaleY() * -1); // Flip bird vertically
        }












    }















}

