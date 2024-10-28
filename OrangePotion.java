public class OrangePotion extends Potion {
    
    public OrangePotion(int posX, int posY) {
        super(posX, posY, new String[] { "/img/OrangePotion.png" }); 
    }    
    
    @Override
    public void triggerAction(GameModel gameModel) {
        // Supprime la potion de la carte
        setPosX(gameModel.getVoidXY());
        setPosY(gameModel.getVoidXY());

        // Bloque les mouvements des gardiens orange
        for (OrangeGuardian orangeGuardian : gameModel.getOrangeGuardiansList()) {
            orangeGuardian.blockMovementByPotion();
        }
    }
}
