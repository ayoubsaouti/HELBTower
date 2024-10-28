import java.util.Random;

public class BlueGuardian extends Guardian {

    private Random random;
    private GameActionElement targetObject;

    public BlueGuardian(int posX, int posY, GameModel gameModel) {
        super(posX, posY, new String[] { "/img/guardianBlue.png" }, gameModel);
        this.random = new Random();
    }

    @Override
    public void triggerAction(GameModel gameModel) {
        if (!isMovementBlocked) {
            // Si aucune cible n'a été définie ou si la cible est atteinte, choisir un nouvel objet
            if (targetObject == null || (getPosX() == targetObject.getPosX() && getPosY() == targetObject.getPosY())) {
                chooseNextTargetObject();
            }

            // Déplace le gardien vers la cible actuelle
            moveToTarget();
        }
    }

    private void moveToTarget() {
        if (targetObject != null) {
            int targetX = targetObject.getPosX();
            int targetY = targetObject.getPosY();

            int currentX = getPosX();
            int currentY = getPosY();

            // Se déplace vers l'objet en réduisant la plus grande distance en premier
            if (Math.abs(currentX - targetX) > Math.abs(currentY - targetY)) {
                if (currentX < targetX) moveRight(); // se déplace à droite
                else if (currentX > targetX) moveLeft(); // se déplace à gauche
            } else {
                if (currentY < targetY) moveDown(); // se déplace en bas
                else if (currentY > targetY) moveUp(); // se déplace en haut
            }
        }
    }

    private void chooseNextTargetObject() {
        int numberOfObjects = gameModel.getObjectForBlueGuardian().size();
        if (numberOfObjects > 0) {
            int objectIndex = random.nextInt(numberOfObjects);
            targetObject = gameModel.getObjectForBlueGuardian().get(objectIndex);
        }
    }
}
