import java.util.Random;

public class Portal extends GameActionElement {

    private Random random;

    public Portal(int posX, int posY) {
        super(posX, posY, new String[] { "/img/portal.png" });
        this.random = new Random();
    }

    @Override
    public void triggerAction(GameModel gameModel) {
        // position du héros
        int heroX = gameModel.getHero().getPosX();
        int heroY = gameModel.getHero().getPosY();

        // si le héros et le portail sont sur la même case
        if (this.getPosX() == heroX && this.getPosY() == heroY) {
            // téléportation
            teleportHeroTower(gameModel);
        }
    }

    public void teleportHeroTower(GameModel gameModel) {
        Tower targetTower = getRandomTower(gameModel);

        if (targetTower != null) {
            // tp  héros à celle de la tour cible
            gameModel.getHero().setPosX(targetTower.getPosX());
            gameModel.getHero().setPosY(targetTower.getPosY());
        }
    }

    public Tower getRandomTower(GameModel gameModel) {
        // nombre de tours
        int numberOfTowers = gameModel.getTowerList().size();

        if (numberOfTowers > 0) {
            // choisir une tour au hasard
            int towerIndex = random.nextInt(numberOfTowers);
            return gameModel.getTowerList().get(towerIndex);
        }

        return null; // Retourner null si aucune tour n'est disponible
    }
}
