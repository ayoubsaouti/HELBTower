public class RedGuardian extends Guardian{
    
    public RedGuardian(int posX, int posY,GameModel gameModel) {
        super(posX, posY, new String[] { "/img/guardianRed.png"},gameModel);    
    }

    @Override
    public void triggerAction(GameModel gameModel){
        if(!isMovementBlocked){
            //LOGIQUE GARDIEN RED
            int numberOfCoins = gameModel.getNumberOfCoins();

            // Se déplace uniquement si le nombre de pièces est impair
            if (numberOfCoins % 2 != 0) {
                goToHero();
            }
        }

    }

    public void goToHero(){
        // Position de l'heros
        int targetX = gameModel.getHero().getPosX();
        int targetY = gameModel.getHero().getPosY();

        int currentX = getPosX();
        int currentY = getPosY();

        // Calcule les distances horizontale et verticale
        // Math.abs pour prendre la valeur absolue
        int distanceX = Math.abs(currentX - targetX);
        int distanceY = Math.abs(currentY - targetY);

         // Se déplace dans la direction de la plus petite distance
         if (distanceX > distanceY) {
            if (currentX < targetX) moveRight();
            else if (currentX > targetX) moveLeft();
        } else {
            if (currentY < targetY) moveDown();
            else if (currentY > targetY) moveUp();
        }

    }
}
