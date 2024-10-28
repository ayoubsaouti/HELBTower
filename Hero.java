public class Hero extends Character {
    private boolean inTower;  // Indique si le héros est dans une tour
    private boolean canMove = true;

    public Hero(int initialX, int initialY,GameModel gameModel) {
        super(initialX, initialY, new String[] { "/img/hero.png" },gameModel);
    }
    
    public boolean isInTower() {
        return inTower;
    }
    
    public void setInTower(boolean inTower) {
        this.inTower = inTower;
    }

    public boolean canMove() {
        return canMove;
    }
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
