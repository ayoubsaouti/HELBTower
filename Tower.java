public class Tower extends GameActionElement {
    private long timeInTower = -1;
    private static final long MAX_TIME_IN_TOWER = 5000; // 5 secondes

    public Tower(int posX, int posY) {
        super(posX, posY, new String[] { "/img/tower.png" }); 
    }

    @Override
    public void triggerAction(GameModel gameModel) {
        Hero hero = gameModel.getHero();
    
        if (hero.getPosX() == this.getPosX() && hero.getPosY() == this.getPosY()) {
            if (timeInTower == -1) {
                timeInTower = System.currentTimeMillis(); // Début du chronomètre
                hero.setInTower(true);
            } else if (System.currentTimeMillis() - timeInTower >= MAX_TIME_IN_TOWER) {
                if (!gameModel.isGodMode()) { // Ne pas terminer le jeu si le mode invincible est activé
                    System.out.println("tower 18");
                    gameModel.setGameOver(true); // Le jeu se termine si le héros reste trop longtemps
                }
            }
        } else {
            resetTimeInTower();
            hero.setInTower(false);
        }
    }

    public void resetTimeInTower() {
        timeInTower = -1; // Réinitialise le temps passé dans la tour
    }
}
