import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SelectionPane extends StackPane{
    protected Image[] backgrounds = new Image[6];
    protected  Image[] foregrounds = new Image[6];
    protected Image[] crosshairs = new Image[7];

    protected ImageView currentForegroundView;
    protected ImageView currentBackgroundView;
    protected ImageView currentCrosshairView;
    public static int currentIndexOfBackground = 0;
    public static int currentIndexOfCrosshair = 0;
    public SelectionPane() {

        // LOADING FOREGROUND BACKGROUND AND CROSSHAIR VIEWS

        for(int i = 0;i < 6; i++ ){


            backgrounds[i] = new Image(DuckHunt.URL_BASE+"background/"+(i+1)+".png");
            foregrounds[i] = new Image(DuckHunt.URL_BASE+"foreground/"+(i+1)+".png");

        }

        for(int i = 0;i < 7; i++ ){

            crosshairs[i] = new Image(DuckHunt.URL_BASE+"crosshair/"+(i+1)+".png");
        }

        // SELECTING AND ARRANGING FOREGROUND BACKGROUND AND CROSSHAIR VIEWS
        currentForegroundView = new ImageView(foregrounds[currentIndexOfBackground]);
        currentForegroundView.setFitHeight(foregrounds[currentIndexOfBackground].getHeight()*DuckHunt.SCALE);
        currentForegroundView.setFitWidth(foregrounds[currentIndexOfBackground].getWidth()*DuckHunt.SCALE);

        currentBackgroundView = new ImageView(backgrounds[currentIndexOfBackground]);
        currentBackgroundView.setFitHeight(backgrounds[currentIndexOfBackground].getHeight()*DuckHunt.SCALE);
        currentBackgroundView.setFitWidth(backgrounds[currentIndexOfBackground].getWidth()*DuckHunt.SCALE);

        currentCrosshairView = new ImageView(crosshairs[currentIndexOfCrosshair]);
        currentCrosshairView.setFitHeight(crosshairs[currentIndexOfCrosshair].getHeight()*DuckHunt.SCALE);
        currentCrosshairView.setFitWidth(crosshairs[currentIndexOfCrosshair].getWidth()*DuckHunt.SCALE);


        // creating texts for Selection Pane
        Text text = new Text("USE ARROW KEYS TO NAVIGATE\n       PRESS ENTER TO START\n           PRESS ESC TO EXIT");
        text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25*DuckHunt.SCALE/3));
        text.setFill(Color.ORANGE);
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(25*DuckHunt.SCALE/3,0,0,202*DuckHunt.SCALE/3));
        hBox.getChildren().add(text);


        getChildren().addAll(currentBackgroundView,currentCrosshairView,hBox);



    }


    /**
     * @param currentBackgroundView get's current background to check boundaries.
     * @param direction to understand which key it's pressed
     */
    public void setCurrentBackgroundView(ImageView currentBackgroundView,String direction) {

        if(currentIndexOfBackground != 5 && direction.equals("RIGHT")){
            currentIndexOfBackground++;
            currentBackgroundView.setImage(backgrounds[currentIndexOfBackground]);
        } else if (currentIndexOfBackground != 0 && direction.equals("LEFT")) {
            currentIndexOfBackground--;
            currentBackgroundView.setImage(backgrounds[currentIndexOfBackground]);
        }


    }


    /**
     * @param currentCrosshairView get's current crosshair to check boundaries.
     * @param direction  to understand which key it's pressed
     */
    public void setCurrentCrosshairView(ImageView currentCrosshairView,String direction){

        if(currentIndexOfCrosshair != 6 && direction.equals("UP")){
            currentIndexOfCrosshair++;
            currentCrosshairView.setImage(crosshairs[currentIndexOfCrosshair]);
        }else if(currentIndexOfCrosshair != 0 && direction.equals("DOWN")){
            currentIndexOfCrosshair--;
            currentCrosshairView.setImage(crosshairs[currentIndexOfCrosshair]);
        }



    }

    public ImageView getCurrentForegroundView() {
        return currentForegroundView;
    }

    public ImageView getCurrentBackgroundView() {
        return currentBackgroundView;
    }

    public ImageView getCurrentCrosshairView() {
        return currentCrosshairView;
    }


}
