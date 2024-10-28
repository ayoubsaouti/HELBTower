import java.util.Random;

public class OrangeGuardian extends Guardian {

    private Random random;

    public OrangeGuardian(int posX, int posY, GameModel gameModel) {
        super(posX, posY, new String[] { "/img/guardianOrange.png" }, gameModel);
        this.random = new Random();
    }

    @Override
    public void triggerAction(GameModel gameModel) {
        if(!isMovementBlocked){
            moveRandomly();
        }
    }

    private void moveRandomly() {
        int direction = random.nextInt(4); // 0 = haut, 1 = bas, 2 = gauche, 3 = droite

        switch (direction) {
            case 0: moveUp(); break;
            case 1: moveDown(); break;
            case 2: moveLeft(); break;
            case 3: moveRight(); break;
        }
    }
}
