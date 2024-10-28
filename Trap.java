import java.util.Random;

public class Trap extends GameActionElement{


    public Trap(int posX, int posY, String[] imagePaths) {
        super(posX, posY, imagePaths);    
    }    
        
    @Override
    public void triggerAction(GameModel gameModel){
        gameModel.getHero().setCanMove(false); // Bloque le mouvement du héros

        setPosX(gameModel.getVoidXY()); 
        setPosY(gameModel.getVoidXY());
    }
}
