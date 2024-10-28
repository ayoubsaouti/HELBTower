public abstract class Character extends GameActionElement {

    private GameModel gameModel;
    private boolean hasCape;  // Indique si le héros a une cape magique

    public Character(int posX, int posY, String[] imagePaths, GameModel gameModel) {
        super(posX, posY, imagePaths);
        this.gameModel = gameModel;
        this.hasCape = false;  // Initialement, le héros n'a pas de cape
    }

    @Override
    public void triggerAction(GameModel gameModel) {}

    // se déplace à droite
    public void moveRight() {
        int newX = getPosX() + 1;
        if (this instanceof Guardian) {
            if (isValidMove(newX, getPosY())) {
                setPosX(newX);
            }
        } else {
            if (canMoveTo(newX, getPosY())) {
                setPosX(newX);
            } else if (hasCape) {
                setPosX(newX);
                checkAndBreakWall(newX, getPosY());
            }
        }
    }

    // se déplace à gauche
    public void moveLeft() {
        int newX = getPosX() - 1;
        if (this instanceof Guardian) {
            if (isValidMove(newX, getPosY())) {
                setPosX(newX);
            }
        } else {
            if (canMoveTo(newX, getPosY())) {
                setPosX(newX);
            } else if (hasCape) {
                setPosX(newX);
                checkAndBreakWall(newX, getPosY());
            }
        }
    }

    // se déplace en haut
    public void moveUp() {
        int newY = getPosY() - 1;
        if (this instanceof Guardian) {
            if (isValidMove(getPosX(), newY)) {
                setPosY(newY);
            }
        } else {
            if (canMoveTo(getPosX(), newY)) {
                setPosY(newY);
            } else if (hasCape) {
                setPosY(newY);
                checkAndBreakWall(getPosX(), newY);
            }
        }
    }

    // se déplace en bas
    public void moveDown() {
        int newY = getPosY() + 1;
        if (this instanceof Guardian) {
            if (isValidMove(getPosX(), newY)) {
                setPosY(newY);
            }
        } else {
            if (canMoveTo(getPosX(), newY)) {
                setPosY(newY);
            } else if (hasCape) {
                setPosY(newY);
                checkAndBreakWall(getPosX(), newY);
            }
        }
    }

    private boolean canMoveTo(int x, int y) {
        // Un héros peut se déplacer partout sauf à travers les murs
        return !gameModel.positionIsWall(x, y);
    }

    protected boolean isValidMove(int x, int y) {
        // Vérifie que la nouvelle position n'est pas un mur, une tour ou un autre gardien
        return !gameModel.positionIsWall(x, y) && !gameModel.positionIsGuardian(x, y) && !gameModel.positionIsPortal(x, y);
    }

    private void checkAndBreakWall(int x, int y) {
        // Vérifie si le personnage possède une cape magique
        if (hasCape) {
            // Cherche un mur à la position spécifiée (x, y)
            Wall wallToBreak = gameModel.getWallsList().stream()
                .filter(wall -> wall.getPosX() == x && wall.getPosY() == y)
                .findFirst()
                .orElse(null);  // Retourne null si aucun mur n'est trouvé
    
            // Si un mur a été trouvé à la position (x, y)
            if (wallToBreak != null) {
                // Supprime le mur de la liste des murs du modèle de jeu
                gameModel.getWallsList().remove(wallToBreak);
                // Supprime également le mur de la liste générale des éléments du modèle de jeu
                gameModel.getElementsList().remove(wallToBreak);
                // Marque la cape comme utilisée pour empêcher la destruction de plus d'un mur
                hasCape = false;
            }
        }
    }
    

    public void setHasCape(boolean hasCape) {
        this.hasCape = hasCape;
    }
}
