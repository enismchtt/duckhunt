import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class welcomePane  {

    public StackPane welcomePaneCreator() {

        StackPane welcomePane = new StackPane();

        Image welcomeImg = new Image("assets/welcome/1.png");
        ImageView welcomeScreen = new ImageView(welcomeImg);
        welcomeScreen.setFitHeight(welcomeImg.getHeight() * DuckHunt.SCALE);
        welcomeScreen.setFitWidth(welcomeImg.getWidth() * DuckHunt.SCALE);



        // creating two text
        Text text = new Text("PRESS ENTER TO START\n    PRESS ESC TO EXIT");
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(450*DuckHunt.SCALE/3,0,0,110*DuckHunt.SCALE/3));
        hBox.getChildren().add(text);

        text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 49*DuckHunt.SCALE/3));
        text.setFill(Color.ORANGE);

        //animation for text
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), event -> text.setVisible(false));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2),event -> text.setVisible(true));
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);
        timeline.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
        timeline.play();
        welcomePane.getChildren().addAll(welcomeScreen,hBox);





        return welcomePane;




    }


}
