import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;  
import java.util.ArrayList;
import java.util.Map;
import javafx.scene.image.Image;


public class GameView {
    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private static final int rows = 20;
    private static final int columns = rows;
    private static final int SQUARE_SIZE = WIDTH / rows;

    private GraphicsContext gc;

    public GameView(GraphicsContext gc) {
        this.gc = gc;
    }

    public void drawBackground() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("282828"));
                } else {
                    gc.setFill(Color.web("2E2E2E"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public void drawGameElements(ArrayList<GameElement> gameElementList, Map<String, Image> imageMap) {
        for (GameElement element : gameElementList) {
            gc.drawImage(imageMap.get(element.getClass().getName()), 
            element.getPosX() * SQUARE_SIZE, 
            element.getPosY() * SQUARE_SIZE, 
            SQUARE_SIZE, SQUARE_SIZE);
        }
    }
    
    public void drawGameOver(int currentScore, int bestScore) {
        // background écran en noir
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
    
        // Affiche "Game Over" au centre de l'écran
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 30));
        gc.fillText("Game Over", WIDTH / 2 - 100, HEIGHT / 2 - 50);
    
        // Affiche le score actuel et le meilleur score
        gc.setFont(new Font("Arial", 20));
        gc.fillText("Your Score: " + currentScore, WIDTH / 2 - 100, HEIGHT / 2);
        gc.fillText("Best Score: " + bestScore, WIDTH / 2 - 100, HEIGHT / 2 + 30);
    
        // Affiche un message pour recommencer ou quitter
        gc.fillText("Press R to Restart or Q to Quit", WIDTH / 2 - 150, HEIGHT / 2 + 60);
    }
    
    
    public void drawScore(int score) {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 35));
        gc.fillText("Score : " + score, 10, 35);
    }
}
