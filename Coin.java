public class Coin extends GameActionElement {

    public Coin(int posX, int posY) {
        super(posX, posY, new String[] { "/img/coin.png" });
    }

    @Override
    public void triggerAction(GameModel gameModel){
        gameModel.increaseScore(1);
        gameModel.decreaseCoinCounter();
        if (gameModel.getNumberOfCoins() == 0){
            gameModel.setNextLevel(true);
        }
        
        setPosX(gameModel.getVoidXY()) ; 
        setPosY(gameModel.getVoidXY()) ;
    }
}
