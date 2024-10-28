public abstract class Potion extends GameActionElement{
    
    public Potion(int posX, int posY, String[] imagePaths) {
        super(posX, posY, imagePaths);    
    }    
        
    public abstract void triggerAction(GameModel gameModel);

}
