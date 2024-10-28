public abstract class GameElement {

    private int posX;
    private int posY;
    
    private final String[] IMAGE_PATHS;
    
    public GameElement(int posX, int posY, String[] imagePaths){
        this.posX = posX; 
        this.posY = posY;
        this.IMAGE_PATHS = imagePaths;
    }
    
    // Getter et Setter 
    public int getPosX() {return posX;}
    
    public void setPosX(int newPosX) {posX = newPosX;}
    
    public int getPosY() {return posY;}
    
    public void setPosY(int newPosY) {posY = newPosY;}
    
    // Accès aux chemins d'image
    public String getPathToImage() {
        return IMAGE_PATHS[0];  // Retourne le premier chemin d'image par défaut
    }
    
    public String getPathToImage(int index) {
        if (index >= 0 && index < IMAGE_PATHS.length) {
            return IMAGE_PATHS[index];
        } else {
            throw new IndexOutOfBoundsException("Index d'image invalide");
        }
    }
    
    public int getPathToImageLen() {
        return IMAGE_PATHS.length;
    }
    
}
