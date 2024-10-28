public class RedPotion extends Potion {
    
    public RedPotion(int posX, int posY) {
        super(posX, posY, new String[] { "/img/RedPotion.png" }); 
    }    
    
    @Override
    public void triggerAction(GameModel gameModel) {
        // Supprime la potion de la carte
        setPosX(gameModel.getVoidXY());
        setPosY(gameModel.getVoidXY());

        // Bloque les mouvements des gardiens orange
        for (RedGuardian redGuardian : gameModel.getRedGuardiansList()) {
            redGuardian.blockMovementByPotion();
        }
    }
}
