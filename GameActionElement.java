public abstract class GameActionElement extends GameElement{
    
    public GameActionElement(int posX, int posY, String[] imagePaths) {
        super(posX, posY, imagePaths);
    }
    
    // Méthode abstraite que chaque sous-classe doit implémenter
    public abstract void triggerAction(GameModel gameModel);
}
