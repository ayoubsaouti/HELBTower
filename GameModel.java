import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.animation.Timeline;


public class GameModel {
    private final int rows = 20;
    private final int columns = rows;
    private final int midX = columns / 2;
    private final int midY = rows / 2;

    //POSITION INITIAL
    //  en dehors du plateau
    private final int voidXY = -1;

    //  hero
    private final int initialHeroX = columns / 2; // Le milieu en termes de colonnes
    private final int initialHeroY = 3; // La quatrième ligne (index 3)
    
    //  gardiens
    private final int initialBlueGuardianX = midY+1; 
    private final int initialBlueGuardianY = midX -1; 
    private final int initialRedGuardianX = midY-1; 
    private final int initialRedGuardianY = midX+1; 
    private final int initialOrangeGuardianX = midY-1;
    private final int initialOrangeGuardianY = midX-1; 
    private final int initialPurpleGuardianX = midY+1; 
    private final int initialPurpleGuardianY = midX+1; 

    //  tours
    private final int initialTLTowerX = (columns / 4) ; // TOP LEFT
    private final int initialTLTowerY = (rows / 4) ; 
    private final int initialTRTowerX = (columns / 4) * 3; // TOP RIGHT
    private final int initialTRTowerY = (rows / 4) ; 
    private final int initialBLTowerX = (columns / 4) ;  // BOTTOM LEFT
    private final int initialBLTowerY = (rows / 4) * 3;
    private final int initialBRTowerX = (columns / 4) *3;// BOTTOM RIGHT
    private final int initialBRTowerY = (rows / 4) *3;

    // portail
    private final int initialPortalX = midX;
    private final int initialPortalY = 0;


    //NBR TOTAL DE GENERATION
    private final int initialNumberOfCoin = 5;  // Nombre de pièces total a généré
    private final int initialNumberOfRedPotions = 1; // Nombre de potions rouge a généré
    private final int initialNumberOfOrangePotions = 1; // Nombre de potions orange a généré
    private final int initialNumberOfPurplePotions = 1; // Nombre de potions mauve a généré
    private final int initialNumberOfBluePotions = 1; // Nombre de potions bleu a généré
    private final int initialNumberOfCape = 1; // Nombre de cape a généré
    private final int initialNumberOfTrap = 1; // Nombre de trap a généré


    private int numberOfCoins = initialNumberOfCoin; // Nombre de pièces visible
    //liste d'images pour le trap
    private String[] trapImages = {"/img/cape.png","/img/RedPotion.png","/img/BluePotion.png","/img/PurplePotion.png","/img/OrangePotion.png"};

    //INSTANCIATION DES OBJETS
    private Hero hero = new Hero(initialHeroX, initialHeroY, this);
    private Coin coin = new Coin(voidXY, voidXY);
    private Wall wall = new Wall(voidXY, voidXY);
    private BlueGuardian blueGuardian = new BlueGuardian(voidXY, voidXY, this);
    private RedGuardian redGuardian = new RedGuardian(voidXY, voidXY, this);
    private OrangeGuardian orangeGuardian = new OrangeGuardian(voidXY, voidXY, this);
    private PurpleGuardian purpleGuardian = new PurpleGuardian(voidXY, voidXY, this);
    private Tower tower = new Tower(voidXY, voidXY);
    private Portal portal = new Portal(voidXY,voidXY);
    private OrangePotion orangePotion = new OrangePotion(voidXY,voidXY);
    private BluePotion bluePotion = new BluePotion(voidXY,voidXY);
    private RedPotion redPotion = new RedPotion(voidXY,voidXY);
    private PurplePotion purplePotion = new PurplePotion(voidXY,voidXY);
    private Cape cape = new Cape(voidXY,voidXY);
    private Trap trap = new Trap(voidXY,voidXY, new String[]{ selectRandomImage()});


    // NOM DE TOUTE LES CLASSES DANS DES STR
    private String heroClassDescription = hero.getClass().getName();
    private String coinClassDescription = coin.getClass().getName();
    private String wallClassDescription = wall.getClass().getName();
    private String blueGuardianClassDescription = blueGuardian.getClass().getName();
    private String redGuardianClassDescription = redGuardian.getClass().getName();
    private String orangeGuardianClassDescription = orangeGuardian.getClass().getName();
    private String purpleGuardianClassDescription = purpleGuardian.getClass().getName();
    private String towerClassDescription = tower.getClass().getName();
    private String portalClassDescription = portal.getClass().getName();
    private String orangePotionClassDescription = orangePotion.getClass().getName();
    private String bluePotionClassDescription = bluePotion.getClass().getName();
    private String redPotionClassDescription = redPotion.getClass().getName();
    private String purplePotionClassDescription = purplePotion.getClass().getName();
    private String capeClassDescription = cape.getClass().getName();
    private String trapClassDescription = trap.getClass().getName();


    // CREATION DES IMAGES
    private Image heroImage = new Image(hero.getPathToImage());
    private Image coinImage = new Image(coin.getPathToImage());
    private Image wallImage = new Image(wall.getPathToImage());
    private Image blueGuardianImage = new Image(blueGuardian.getPathToImage());
    private Image redGuardianImage = new Image(redGuardian.getPathToImage());
    private Image orangeGuardianImage = new Image(orangeGuardian.getPathToImage());
    private Image purpleGuardianImage = new Image(purpleGuardian.getPathToImage());
    private Image towerImage = new Image(tower.getPathToImage());
    private Image portalImage = new Image(portal.getPathToImage());
    private Image orangePotionImage = new Image(orangePotion.getPathToImage());
    private Image bluePotionImage = new Image(bluePotion.getPathToImage());
    private Image redPotionImage = new Image(redPotion.getPathToImage());
    private Image purplePotionImage = new Image(purplePotion.getPathToImage());
    private Image capeImage = new Image(cape.getPathToImage());
    private Image trapImage = new Image(trap.getPathToImage());


    // CREATION DES COLLECTION
    private ArrayList<GameElement> elementsList = new ArrayList<>();
    private Map<String, Image> imageMap = new HashMap<>();
    private ArrayList<Coin> coinsList = new ArrayList<>();
    private ArrayList<Wall> wallsList = new ArrayList<>();
    private ArrayList<Guardian> guardiansList = new ArrayList<>();
    private ArrayList<BlueGuardian> blueGuardiansList = new ArrayList<>();
    private ArrayList<OrangeGuardian> orangeGuardiansList = new ArrayList<>();
    private ArrayList<RedGuardian> redGuardiansList = new ArrayList<>();
    private ArrayList<PurpleGuardian> purpleGuardiansList = new ArrayList<>();
    private ArrayList<Tower> towerList = new ArrayList<>();
    private ArrayList<Portal> portalList = new ArrayList<>();
    private ArrayList<Potion> potionsList = new ArrayList<>();
    private ArrayList<OrangePotion> orangePotionList = new ArrayList<>();
    private ArrayList<BluePotion> bluePotionList = new ArrayList<>();
    private ArrayList<RedPotion> redPotionList = new ArrayList<>();
    private ArrayList<PurplePotion> purplePotionList = new ArrayList<>();
    private ArrayList<Cape> capeList = new ArrayList<>();
    private ArrayList<GameActionElement> objectForBlueGuardianList = new ArrayList<>();
    private ArrayList<Trap> trapList = new ArrayList<>();


    // hashmap pour les potions consommées
    private Map<String, Boolean> potionsConsumed = new HashMap<>();

    //STRING POUR LES NOMS DES POTIONS
    private final String redPotionString = "RED";
    private final String orangePotionString = "ORANGE";
    private final String bluePotionString = "BLUE";
    private final String purplePotionString = "PURPLE";


    private boolean gameOver = false;
    private boolean nextLevel = false;

    private int score = 0;
    private int bestScore = 0;

    //POSITION RANDOM POUR LES POTIONS
    private Random random = new Random();

    private boolean godMode = false;
    private boolean heroTraped = false;



    public GameModel() {
        elementsList.add(hero);
        imageMap.put(heroClassDescription, heroImage);
        imageMap.put(coinClassDescription, coinImage);
        imageMap.put(wallClassDescription, wallImage);
        imageMap.put(blueGuardianClassDescription, blueGuardianImage);
        imageMap.put(redGuardianClassDescription, redGuardianImage);
        imageMap.put(orangeGuardianClassDescription, orangeGuardianImage);
        imageMap.put(purpleGuardianClassDescription, purpleGuardianImage);
        imageMap.put(towerClassDescription, towerImage);
        imageMap.put(portalClassDescription, portalImage);
        imageMap.put(orangePotionClassDescription, orangePotionImage);
        imageMap.put(bluePotionClassDescription, bluePotionImage);
        imageMap.put(redPotionClassDescription, redPotionImage);
        imageMap.put(purplePotionClassDescription, purplePotionImage);
        imageMap.put(capeClassDescription, capeImage);

        imageMap.put(trapClassDescription, trapImage);


        // Ajout des éléments à la Map
        potionsConsumed.put(redPotionString, false);
        potionsConsumed.put(orangePotionString, false);
        potionsConsumed.put(bluePotionString, false);
        potionsConsumed.put(purplePotionString, false);
    }

    public void resetGameModel() {
        // Vide toutes les listes d'éléments
        elementsList.clear();
        coinsList.clear();
        wallsList.clear();
        guardiansList.clear();
        blueGuardiansList.clear();
        orangeGuardiansList.clear();
        redGuardiansList.clear();
        purpleGuardiansList.clear();
        towerList.clear();
        portalList.clear();
        potionsList.clear();
        orangePotionList.clear();
        redPotionList.clear();
        bluePotionList.clear();
        purplePotionList.clear();
        capeList.clear();
        trapList.clear();
        
        // Réinitialise le héros à sa position initiale
        hero = new Hero(initialHeroX, initialHeroY, this);
        elementsList.add(hero);

        // Réinitialise l'état de chaque tour
        for (Tower tower : towerList) {
            tower.resetTimeInTower();
        }
    
        gameOver = false; // reset état de gameOver à false
        nextLevel = false;
        godMode = false;
        heroTraped = false;
        resetCoinCounter();
    }


    public void eatTrap() {
        for (Trap trap : trapList) {
            if (hero.getPosX() == trap.getPosX() && hero.getPosY() == trap.getPosY()) {
                trap.triggerAction(this);
                heroTraped = true;
            }
        }
    }

    public void generateTrap() {
        // Génération de trap
        for (int i = 0; i < initialNumberOfTrap; i++) {
            int x, y;
            do {
                x = random.nextInt(rows);
                y = random.nextInt(columns);
            } while (isPositionOccupied(x, y));
            
            Trap newTrap = new Trap(x, y, new String[]{selectRandomImage()});
            
            System.out.println("Generated trap at position: (" + x + ", " + y + ")");
            
            trapList.add(newTrap);
            elementsList.add(newTrap);
        }
    }

    private String selectRandomImage() {
        Random random = new Random();
        int index = random.nextInt(trapImages.length);
        return trapImages[index];
    }

    public void eatCape() {
        for (Cape cape : capeList) {
            if (hero.getPosX() == cape.getPosX() && hero.getPosY() == cape.getPosY()) {
                cape.triggerAction(this);
            }
        }
    }
    public void generateCape(){
        // Génére cape
        for (int i = 0; i < initialNumberOfCape; i++) {
            int x, y;
            do {
                x = random.nextInt(rows);
                y = random.nextInt(columns);
            } while (isPositionOccupied(x, y));
            
            Cape newCape = new Cape(x,y);
            capeList.add(newCape);
            objectForBlueGuardianList.add(newCape);
            elementsList.add(newCape);
        }
    }
    
    public void generateOrangePotions() {
    
        // Génére des potions orange
        for (int i = 0; i < initialNumberOfOrangePotions; i++) {
            int x, y;
            do {
                x = random.nextInt(rows);
                y = random.nextInt(columns);
            } while (isPositionOccupied(x, y));
            
            OrangePotion newOrangePotion = new OrangePotion(x,y);
            orangePotionList.add(newOrangePotion);
            potionsList.add(newOrangePotion);
            objectForBlueGuardianList.add(newOrangePotion);
            elementsList.add(newOrangePotion);
        }
    }
    public void generateBluePotions() {
    
        // Génére des potions blue
        for (int i = 0; i < initialNumberOfBluePotions; i++) {
            int x, y;
            do {
                x = random.nextInt(rows);
                y = random.nextInt(columns);
            } while (isPositionOccupied(x, y));
            
            BluePotion newBluePotion = new BluePotion(x,y);
            bluePotionList.add(newBluePotion);
            potionsList.add(newBluePotion);
            objectForBlueGuardianList.add(newBluePotion);
            elementsList.add(newBluePotion);
        }
    }
    public void generateRedPotions() {
    
        // Génére des potions red
        for (int i = 0; i < initialNumberOfRedPotions; i++) {
            int x, y;
            do {
                x = random.nextInt(rows);
                y = random.nextInt(columns);
            } while (isPositionOccupied(x, y));
            
            RedPotion newRedPotion = new RedPotion(x,y);
            redPotionList.add(newRedPotion);
            potionsList.add(newRedPotion);
            objectForBlueGuardianList.add(newRedPotion);
            elementsList.add(newRedPotion);
        }
    }
    public void generatePurplePotions() {
    
        // Génére des potions purple
        for (int i = 0; i < initialNumberOfPurplePotions; i++) {
            int x, y;
            do {
                x = random.nextInt(rows);
                y = random.nextInt(columns);
            } while (isPositionOccupied(x, y));
            
            PurplePotion newPurplePotion = new PurplePotion(x,y);
            purplePotionList.add(newPurplePotion);
            potionsList.add(newPurplePotion);
            objectForBlueGuardianList.add(newPurplePotion);
            elementsList.add(newPurplePotion);
        }
    }


    public boolean eatPotion() {
        for (Potion potion : potionsList) {
            if (hero.getPosX() == potion.getPosX() && hero.getPosY() == potion.getPosY()) {
                if (potion instanceof OrangePotion) {
                    potion.triggerAction(this);
                    potionsConsumed.put(orangePotionString, true); // Potion orange consommée
                    return true;
                } else if (potion instanceof BluePotion) {
                    potion.triggerAction(this);
                    potionsConsumed.put(bluePotionString, true); // Potion bleue consommée
                    return true;
                } else if (potion instanceof RedPotion) {
                    potion.triggerAction(this);
                    potionsConsumed.put(redPotionString, true); // Potion rouge consommée
                    return true;
                } else if (potion instanceof PurplePotion) {
                    potion.triggerAction(this);
                    potionsConsumed.put(purplePotionString, true); // Potion pourpre consommée
                    return true;
                }
            }
        }
        return false;
    }

    public void generatePortal(){
        Portal newPortal = new Portal(initialPortalX, initialPortalY);
        portalList.add(newPortal);
        elementsList.add(newPortal);
    }

    public boolean checkCollisionWithGuardians() {
        if (isGodMode()) {
            return false; // Pas de collision si le god mod est activé
        }
        for (Guardian guardian : guardiansList) {
            if (hero.getPosX() == guardian.getPosX() && hero.getPosY() == guardian.getPosY()) {
                if (!hero.isInTower()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    

    public void generateTowers() {
        Tower towerTL = new Tower(initialTLTowerX, initialTLTowerY);
        towerList.add(towerTL);
        elementsList.add(towerTL);

        Tower towerTR = new Tower(initialTRTowerX, initialTRTowerY);
        towerList.add(towerTR);
        elementsList.add(towerTR);

        Tower towerBL = new Tower(initialBLTowerX, initialBLTowerY);
        towerList.add(towerBL);
        elementsList.add(towerBL);

        Tower towerBR = new Tower(initialBRTowerX, initialBRTowerY);
        towerList.add(towerBR);
        elementsList.add(towerBR);
    }
    
    public void generateBlueGuardian(int posX, int posY) {
        BlueGuardian newBlueGuardian = new BlueGuardian(posX, posY, this);
        blueGuardiansList.add(newBlueGuardian);
        elementsList.add(newBlueGuardian);
        guardiansList.add(newBlueGuardian);
    }

    public void generateOrangeGuardian(int posX, int posY) {
        OrangeGuardian newOrangeGuardian = new OrangeGuardian(posX, posY, this);
        orangeGuardiansList.add(newOrangeGuardian);
        elementsList.add(newOrangeGuardian);
        guardiansList.add(newOrangeGuardian);
    }

    public void generateRedGuardian(int posX, int posY) {
        RedGuardian newRedGuardian = new RedGuardian(posX, posY, this);
        redGuardiansList.add(newRedGuardian);
        elementsList.add(newRedGuardian);
        guardiansList.add(newRedGuardian);
    }

    public void generatePurpleGuardian(int posX, int posY) {
        PurpleGuardian newPurpleGuardian = new PurpleGuardian(posX, posY, this);
        purpleGuardiansList.add(newPurpleGuardian);
        elementsList.add(newPurpleGuardian);
        guardiansList.add(newPurpleGuardian);
    }

    // génére tous les gardiens à leurs positions initiales
    public void generateGuardians() {
        generateBlueGuardian(initialBlueGuardianX, initialBlueGuardianY);
        generateOrangeGuardian(initialOrangeGuardianX, initialOrangeGuardianY);
        generateRedGuardian(initialRedGuardianX, initialRedGuardianY);
        generatePurpleGuardian(initialPurpleGuardianX, initialPurpleGuardianY);
    }
    
    public void generateCoins(int numberOfCoins){
        for (int i = 0; i < numberOfCoins; i++) {
            addNewCoins();
        }
    }

    public void addNewCoins() {
        int x;
        int y;

        do {
            x = (int) (Math.random() * rows);
            y = (int) (Math.random() * columns);
        } while (isPositionOccupied(x, y));
    
        Coin newCoin = new Coin(x, y);
        coinsList.add(newCoin);
        objectForBlueGuardianList.add(newCoin);
        elementsList.add(newCoin);
    }

    public void generateWalls() {
        // Génère des murs en haut et en bas
        for (int x = 0; x < columns; x++) {
            Wall topWall = new Wall(x, 0);
            Wall bottomWall = new Wall(x, rows - 1);
            wallsList.add(topWall);
            elementsList.add(topWall);
            wallsList.add(bottomWall);
            elementsList.add(bottomWall);
        }  
    
        // Génère des murs à gauche et à droite
        for (int y = 1; y < rows - 1; y++) {  // On commence à 1 et on finit à rows - 1 pour pas dupliquer les wall des corner
            Wall leftWall = new Wall(0, y);
            Wall rightWall = new Wall(columns - 1, y);
            wallsList.add(leftWall);
            elementsList.add(leftWall);
            wallsList.add(rightWall);
            elementsList.add(rightWall);
        }
    
        // Position de départ pour la croix 7x7
        int startX = midX - 3;
        int startY = midY - 3;
        int endX = startX + 7;
        int endY = startY + 7;

        // Génère la croix 7x7
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                // Ajoute des murs seulement pour former une croix
                if (x == midX || y == midY) {
                    Wall middleWall = new Wall(x, y);
                    wallsList.add(middleWall);
                    elementsList.add(middleWall);
                }
            }
        }

        wallsList.removeIf(wall -> wall.getPosX() == initialPortalX
                        && wall.getPosY() == initialPortalY);

        elementsList.removeIf(element -> element instanceof Wall
                            && ((Wall) element).getPosX() == initialPortalX 
                            && ((Wall) element).getPosY() == initialPortalY);
    }

    public void eatCoin() {
        for (Coin coin : coinsList) {
            if (hero.getPosX() == coin.getPosX() && hero.getPosY() == coin.getPosY()) {
                coin.triggerAction(this);
            }
        }
    }

    public boolean isHeroInTower() {
        for (Tower tower : towerList) {
            if (hero.getPosX() == tower.getPosX() && hero.getPosY() == tower.getPosY()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isPositionOccupied(int x, int y) {
        for (GameElement element : elementsList) {
            if (element.getPosX() == x && element.getPosY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean positionIsWall(int x, int y) {
        for (Wall wall : wallsList) {
            if (wall.getPosX() == x && wall.getPosY() == y) {
                return true;
            }
        }
        return false;
    }
    public boolean positionIsGuardian(int x, int y) {
        for (Guardian guardian : guardiansList) {
            if (guardian.getPosX() == x && guardian.getPosY() == y) {
                return true;
            }
        }
        return false;
    }
    public boolean positionIsTower(int x, int y) {
        for (Tower tower : towerList) {
            if (tower.getPosX() == x && tower.getPosY() == y) {
                return true;
            }
        }
        return false;
    }
    public boolean positionIsPortal(int x, int y) {
        for (Portal portal : portalList) {
            if (portal.getPosX() == x && portal.getPosY() == y) {
                return true;
            }
        }
        return false;
    }
    public void resetScore () {
        score = 0;
    }
    public void increaseScore(int points) {
        score += points;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isNextLevel() {
        return nextLevel;
    }
    public void setGameOver(boolean gameOver) {
        System.out.println("GAME OVER TRUE");

        this.gameOver = gameOver;
        
        // Met à jour le meilleur score si le score actuel est supérieur
        if (score > bestScore) {
            bestScore = score;
        }
    }
    public void setNextLevel(boolean nextLevel) {
        this.nextLevel = nextLevel;
    }
    public void decreaseCoinCounter(){
        numberOfCoins--;
    }   
    public void resetCoinCounter(){
        numberOfCoins = initialNumberOfCoin;
    }   

    // GET
    public Hero getHero() {
        return hero;
    }

    public int getVoidXY() {
        return voidXY;
    }

    public Image getCoinImage() {
        return coinImage;
    }

    public Image getHeroImage() {
        return heroImage;
    }

    public Map<String, Image> getImageMap() {
        return imageMap;
    }

    public int getNumberOfCoins() {
        return numberOfCoins;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public ArrayList<Coin> getCoinsList() {
        return coinsList;
    }

    public ArrayList<GameElement> getElementsList() {
        return elementsList;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Tower> getTowerList() {
        return towerList;
    }

    public ArrayList<Guardian> getGuardiansList() {
        return guardiansList;
    }

    public ArrayList<Portal> getPortalList() {
        return portalList;
    }

    public int getBestScore() {
        return bestScore;
    }

    public ArrayList<BlueGuardian> getBlueGuardiansList() {
        return blueGuardiansList;
    }

    public ArrayList<OrangeGuardian> getOrangeGuardiansList() {
        return orangeGuardiansList;
    }

    public ArrayList<RedGuardian> getRedGuardiansList() {
        return redGuardiansList;
    }

    public ArrayList<PurpleGuardian> getPurpleGuardiansList() {
        return purpleGuardiansList;
    }

    public Map<String, Boolean> getPotionsConsumed() {
        return potionsConsumed;
    }

    public void setPotionsConsumed(Map<String, Boolean> potionsConsumed) {
        this.potionsConsumed = potionsConsumed;
    }

    public ArrayList<Wall> getWallsList() {
        return wallsList;
    }

    public ArrayList<GameActionElement> getObjectForBlueGuardian() {
        return objectForBlueGuardianList;
    }
    public boolean isGodMode() {
        return godMode;
    }

    public void toggleGodMode() {
        godMode = !godMode;
    }

    public boolean isHeroTraped() {
        return heroTraped;
    }
    
}
