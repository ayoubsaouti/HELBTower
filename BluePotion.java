public class BluePotion extends Potion {
    
    public BluePotion(int posX, int posY) {
        super(posX, posY, new String[] { "/img/BluePotion.png" }); 
    }    
    
    @Override
    public void triggerAction(GameModel gameModel) {
        // Supprime la potion de la carte
        setPosX(gameModel.getVoidXY());
        setPosY(gameModel.getVoidXY());

        // Bloque les mouvements des gardiens bleu
        for (BlueGuardian blueGuardian : gameModel.getBlueGuardiansList()) {
            blueGuardian.blockMovementByPotion();
        }
    }
}
