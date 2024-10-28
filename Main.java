import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;

    // méthode start est le point d'entrée du programme
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HELBTower");

        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        GameModel model = new GameModel();
        GameView view = new GameView(gc);
        GameController controller = new GameController(model, view);

        scene.setOnKeyPressed(event -> controller.handleKeyEvent(event));
    }

    // Le main qui lance l'application JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}