public class PurplePotion extends Potion {
    
    public PurplePotion(int posX, int posY) {
        super(posX, posY, new String[] { "/img/PurplePotion.png" }); 
    }    
    
    @Override
    public void triggerAction(GameModel gameModel) {
        // Supprime la potion de la carte
        setPosX(gameModel.getVoidXY());
        setPosY(gameModel.getVoidXY());

        // Bloque les mouvements des gardiens orange
        for (PurpleGuardian purpleGuardian : gameModel.getPurpleGuardiansList()) {
            purpleGuardian.blockMovementByPotion();
        }
    }
}
