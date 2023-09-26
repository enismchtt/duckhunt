import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.scene.text.Text;




public class Level3 extends Pane {


    private  double BIRD_WIDTH = 80 * DuckHunt.SCALE / 3;
    private   double BIRD_HEIGHT = 60 * DuckHunt.SCALE / 3;
    private  double birdSpeed1 = 50 * DuckHunt.SCALE / 3;
    private double birdSpeed2 = 50 * DuckHunt.SCALE / 3;


    private  int numberOfAmmo = 6;
    private  int numberOfBird = 2;

    public static boolean gameWin = false;
    public static  boolean gameLose = false;
    public static boolean gameEnd = false;


    protected ImageView currentForegroundView;
    protected ImageView currentBackgroundView;

    protected ImageView cursorImageView;
    private ImageView birdView1;
    private ImageView birdView2;
    private int currentFrameIndex1 = 0;
    private int currentFrameIndex2 = 0;
    private Image[] firstBirdHorizontalImages = new Image[3];
    private Image[] secondBirdHorizontalImages = new Image[3];

    private Image[] birdFallImages = new Image[2];

    public Level3() {


        // getting current views
        SelectionPane sp = new SelectionPane();
        currentBackgroundView = sp.getCurrentBackgroundView();
        cursorImageView = sp.getCurrentCrosshairView();
        currentForegroundView = sp.getCurrentForegroundView();


        // first and second bird images for horizontal animation and falling animation
        firstBirdHorizontalImages[0] = new Image("assets/duck_red/4.png");
        firstBirdHorizontalImages[1] = new Image("assets/duck_red/5.png");
        firstBirdHorizontalImages[2] = new Image("assets/duck_red/6.png");

        secondBirdHorizontalImages[0] = new Image("assets/duck_black/4.png");
        secondBirdHorizontalImages[1] = new Image("assets/duck_black/5.png");
        secondBirdHorizontalImages[2] = new Image("assets/duck_black/6.png");



        birdFallImages[0] = new Image("assets/duck_red/7.png");
        birdFallImages[1] = new Image("assets/duck_red/8.png");

        birdView1 = new ImageView(firstBirdHorizontalImages[0]);
        birdView1.setFitWidth(BIRD_WIDTH);
        birdView1.setFitHeight(BIRD_HEIGHT);
        birdView1.setLayoutY(100*DuckHunt.SCALE/3);

        birdView2 = new ImageView(secondBirdHorizontalImages[0]);
        birdView2.setFitWidth(BIRD_WIDTH);
        birdView2.setFitHeight(BIRD_HEIGHT);
        birdView2.setLayoutY(300*DuckHunt.SCALE/3);
        birdView2.setLayoutX(240*DuckHunt.SCALE/3);



        //creating text
        Text level = new Text("Level 3/6");
        level.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25*DuckHunt.SCALE/3));
        level.setFill(Color.DARKORANGE);
        Text numOfAmmo = new Text("Ammo Left: "+String.valueOf(numberOfAmmo));
        numOfAmmo.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25*DuckHunt.SCALE/3));
        numOfAmmo.setFill(Color.DARKORANGE);

        HBox hBoxForTexts = new HBox(170*DuckHunt.SCALE/3);
        hBoxForTexts.getChildren().addAll(level,numOfAmmo);
        hBoxForTexts.setAlignment(javafx.geometry.Pos.CENTER);
        hBoxForTexts.setPadding(new Insets(0,0,0,325*DuckHunt.SCALE/3));



        cursorImageView.setFitWidth(35*DuckHunt.SCALE/3); // Set the width of the cursor image
        cursorImageView.setFitHeight(35*DuckHunt.SCALE/3); // Set the width of the cursor image
        //cursorImageView.setPreserveRatio(true); // Maintain the aspect ratio of the image


        // Set custom cursor
        setOnMouseMoved(event -> {
            cursorImageView.setTranslateX(event.getX() - cursorImageView.getBoundsInLocal().getWidth() / 2);
            cursorImageView.setTranslateY(event.getY() - cursorImageView.getBoundsInLocal().getHeight() / 2);
        });
        setCursor(javafx.scene.Cursor.NONE);

        // add whole to to pane
        getChildren().addAll(currentBackgroundView, birdView1,birdView2,currentForegroundView,hBoxForTexts,cursorImageView);



        // Implement shooting functionality
        setOnMouseClicked(event -> {


            if (event.getButton() == MouseButton.PRIMARY && (!gameEnd)) {

                // gunshot sound
                DuckHunt.gunSoundPlayer.play();
                DuckHunt.gunSoundPlayer.seek(Duration.ZERO);


                numberOfAmmo--;
                numOfAmmo.setText("Ammo Left: "+String.valueOf(numberOfAmmo));
                double mouseX = event.getX();
                double mouseY = event.getY();
                // Understanding how the birds' hitten
                if(birdView1.getBoundsInParent().contains(mouseX, mouseY)){
                    birdView1.setImage(birdFallImages[0]);
                    getChildren().remove(birdView1);
                    numberOfBird--;
                }
                if(birdView2.getBoundsInParent().contains(mouseX, mouseY)){
                    birdView2.setImage(birdFallImages[0]);
                    getChildren().remove(birdView2);
                    numberOfBird--;
                }








                if((numberOfBird == 0) && (numberOfAmmo >= 0)){
                    // win
                    // creating two text
                    Text youWinText = new Text("YOU WIN!");
                    youWinText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 80*DuckHunt.SCALE/3));
                    youWinText.setFill(Color.DARKORANGE);
                    Text pressEnterText = new Text("Press ENTER to play next level");
                    pressEnterText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 45*DuckHunt.SCALE/3));
                    pressEnterText.setFill(Color.DARKORANGE);
                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(youWinText,pressEnterText);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setPadding(new Insets(200*DuckHunt.SCALE/3,0,0,70*DuckHunt.SCALE/3));
                    getChildren().add(vBox);


                    // gameWin sound starts
                    DuckHunt.winSoundPlayer.play();



                    //animation for text
                    KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), event2 -> pressEnterText.setVisible(false));
                    KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2),event2 -> pressEnterText.setVisible(true));
                    Timeline timeline1= new Timeline(keyFrame1, keyFrame2);
                    timeline1.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
                    timeline1.play();
                    gameWin = true;
                    gameEnd = true;


                }else if((!(numberOfBird == 0)) && (numberOfAmmo == 0)){
                    // lose

                    // creating two text
                    Text youLoseText = new Text("GAME OVER!");
                    youLoseText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 80*DuckHunt.SCALE/3));
                    youLoseText.setFill(Color.DARKORANGE);
                    Text pressEnterEscText = new Text("Press ENTER to play again\n        Press ESC to exit");
                    pressEnterEscText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 45*DuckHunt.SCALE/3));
                    pressEnterEscText.setFill(Color.DARKORANGE);
                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(youLoseText,pressEnterEscText);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setPadding(new Insets(200*DuckHunt.SCALE/3,0,0,95*DuckHunt.SCALE/3));
                    getChildren().add(vBox);

                    // game over sound plays
                    DuckHunt.gameOverSoundPlayer.play();



                    //animation for text
                    KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), event1 -> pressEnterEscText.setVisible(false));
                    KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2),event1 -> pressEnterEscText.setVisible(true));
                    Timeline timeline2= new Timeline(keyFrame1, keyFrame2);
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
        birdView1.setLayoutX(birdView1.getLayoutX() + birdSpeed1);
        birdView2.setLayoutX(birdView2.getLayoutX() + birdSpeed2);

        // Update birds frame
        currentFrameIndex1 = (currentFrameIndex1 + 1) % firstBirdHorizontalImages.length;
        birdView1.setImage(firstBirdHorizontalImages[currentFrameIndex1]);

        currentFrameIndex2 = (currentFrameIndex2 + 1) % secondBirdHorizontalImages.length;
        birdView2.setImage(secondBirdHorizontalImages[currentFrameIndex2]);
        
        

        // Reverse direction if birds hits the border
        if (birdView1.getLayoutX() <= 0 || birdView1.getLayoutX() >= getWidth() - BIRD_WIDTH) {
            birdSpeed1 = -birdSpeed1;
            birdView1.setScaleX(birdView1.getScaleX() * -1); // Flip bird horizontally
        }
        if (birdView2.getLayoutX() <= 0 || birdView2.getLayoutX() >= getWidth() - BIRD_WIDTH) {
            birdSpeed2 = -birdSpeed2;
            birdView2.setScaleX(birdView2.getScaleX() * -1); // Flip bird horizontally
        }


    }










}



