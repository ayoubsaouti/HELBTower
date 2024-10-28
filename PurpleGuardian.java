import java.util.Random;

public class PurpleGuardian extends Guardian {

    private Tower targetTower;
    private Random random;

    public PurpleGuardian(int posX, int posY, GameModel gameModel) {
        super(posX, posY, new String[] { "/img/guardianPurple.png" }, gameModel);
        this.random = new Random();
    }

    @Override
    public void triggerAction(GameModel gameModel) {
        if(!isMovementBlocked){
            moveToTargetTower();
        }
    }

    private void moveToTargetTower() {
        // Si aucune tour cible n'a été définie, en choisir une
        if (targetTower == null) {
            chooseNextTargetTower();
        } 
    
        // Vérifie si la tour cible est définie
        if (targetTower != null) {
            int targetX = targetTower.getPosX();
            int targetY = targetTower.getPosY();
            int currentX = getPosX();
            int currentY = getPosY();
    
            // Calcule les distances avc la valeur absolue
            int distanceX = Math.abs(currentX - targetX);
            int distanceY = Math.abs(currentY - targetY);
    
            // Se déplace vers la tour en réduisant la plus grande distance en premier
            if (distanceX > distanceY) {
                if (currentX < targetX) moveRight(); // se déplace à droite
                else if (currentX > targetX) moveLeft(); // se déplace à gauche
            } else {
                if (currentY < targetY) moveDown(); // se déplace en bas
                else if (currentY > targetY) moveUp(); // se déplace en haut
            }
    
            // Vérifie si le personnage est arrivé à la tour cible
            if (currentX == targetX && currentY == targetY) {
                // arrivé à la tour, choisir une nouvelle tour comme cible
                chooseNextTargetTower();
            }
        }
    }
    
    //  choisir une nouvelle tour cible
    private void chooseNextTargetTower() {
        int numberOfTowers = gameModel.getTowerList().size();
        if (numberOfTowers > 0) {
            int towerIndex = random.nextInt(numberOfTowers);
            targetTower = gameModel.getTowerList().get(towerIndex);
        } else {
            targetTower = null; // Aucun cible si aucune tour disponible
        }
    }
}
