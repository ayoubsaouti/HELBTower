public class Cape extends GameActionElement{
    
    public Cape(int posX, int posY) {
        super(posX, posY, new String[] { "/img/cape.png" });    
    }    
        
    public void triggerAction(GameModel gameModel){
        setPosX(gameModel.getVoidXY()) ; 
        setPosY(gameModel.getVoidXY()) ;
        gameModel.getHero().setHasCape(true);
    }

}
