public abstract class Guardian extends Character{

    protected GameModel gameModel;
    protected boolean isMovementBlocked;

    public Guardian(int posX, int posY, String[] imagePaths,GameModel gameModel) {
        super(posX, posY, imagePaths,gameModel);    
        this.gameModel = gameModel;
        isMovementBlocked = false;
    }

    @Override
    public abstract void triggerAction(GameModel gameModel);

    // Méthode pour bloquer le mouvement
    public void blockMovementByPotion() {
        this.isMovementBlocked = true;
    }

    // Méthode pour débloquer le mouvement
    public void unblockMovementByPotion() {
        this.isMovementBlocked = false;
    }

    public void toggleMovement() {
        isMovementBlocked = !isMovementBlocked;
    }
}
