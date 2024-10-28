import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.application.Platform;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class GameController {
    private GameModel model;
    private GameView view;

    // Timeline 
    private Timeline gameTimeLine;
    private Timeline orangePotionTimeline;
    private Timeline bluePotionTimeline;
    private Timeline purplePotionTimeline;
    private Timeline redPotionTimeline;
    private Timeline heroBlockTimeline;

    private Random random = new Random();


    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

        newGame();
    }

    public void newGame(){
        model.generateWalls();
        model.generateTowers();
        model.generateGuardians();
        model.generatePortal();
        model.generateOrangePotions();
        model.generateBluePotions();
        model.generateRedPotions();
        model.generatePurplePotions();
        model.generateCape();
        model.generateTrap();

        model.generateCoins(model.getNumberOfCoins());

        gameTimeLine = new Timeline(new KeyFrame(Duration.millis(200), e -> update()));
        gameTimeLine.setCycleCount(Timeline.INDEFINITE);
        gameTimeLine.play();

    }

    public void restartGame() {
        model.resetGameModel();
        gameTimeLine.stop(); // Arrêter l'ancienne timeline
        stopAllTimelines();

        newGame();
    }


    public void update() {
        ifNextLevel();
        draw();
        teleportHero();
        collisionWithGuardians();
        checkHeroInTower();
        checkGameOver();
        moveGuardians();
        // Vérification si le héros mange 
        model.eatCoin();
        model.eatPotion();
        model.eatCape();
        model.eatTrap();
        checkPotions();

        model.getHero().setInTower(model.isHeroInTower());

        if(model.isHeroTraped()){
            handleTrapTrigger();
        }
    }


    private void stopAllTimelines() {
        if (orangePotionTimeline != null) orangePotionTimeline.stop();
        if (bluePotionTimeline != null) bluePotionTimeline.stop();
        if (purplePotionTimeline != null) purplePotionTimeline.stop();
        if (redPotionTimeline != null) redPotionTimeline.stop();
        if (heroBlockTimeline != null) heroBlockTimeline.stop();
    }

    // gére la prise d'un piège et bloquer le mouvement du héros
    public void handleTrapTrigger() {

        // timeline qui débloque le mouvement après 5 secondes
        heroBlockTimeline = new Timeline(
            new KeyFrame(Duration.seconds(5), e -> model.getHero().setCanMove(true))
        );
        heroBlockTimeline.setCycleCount(1);
        heroBlockTimeline.play();
    }

    private void checkPotions() {
        Map<String, Boolean> potionsConsumed = model.getPotionsConsumed();

        boolean isOrangePotionConsumed = potionsConsumed.get("ORANGE");
        boolean isRedPotionConsumed = potionsConsumed.get("RED");
        boolean isPurplePotionConsumed = potionsConsumed.get("PURPLE");
        boolean isBluePotionConsumed = potionsConsumed.get("BLUE");

        if (isOrangePotionConsumed) {
            // Débloque les gardiens orange après 5 secondes
            orangePotionTimeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> {
                    model.getOrangeGuardiansList().forEach(OrangeGuardian::unblockMovementByPotion);
                })
            );
            orangePotionTimeline.setCycleCount(1);
            orangePotionTimeline.play();
            potionsConsumed.put("ORANGE", false);
            model.setPotionsConsumed(potionsConsumed);        
        } else if (isBluePotionConsumed) {
            // Débloque les gardiens blue après 5 secondes
            bluePotionTimeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> {
                    model.getBlueGuardiansList().forEach(BlueGuardian::unblockMovementByPotion);
                })
            );
            bluePotionTimeline.setCycleCount(1);
            bluePotionTimeline.play();
            potionsConsumed.put("BLUE", false);
            model.setPotionsConsumed(potionsConsumed);        
        } else if (isRedPotionConsumed) {
            // Débloque les gardiens red après 5 secondes
            redPotionTimeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> {
                    model.getRedGuardiansList().forEach(RedGuardian::unblockMovementByPotion);
                })
            );
            redPotionTimeline.setCycleCount(1);
            redPotionTimeline.play();
            potionsConsumed.put("RED", false);
            model.setPotionsConsumed(potionsConsumed);        
        } else if (isPurplePotionConsumed) {
            // Débloque les gardiens purple après 5 secondes
            purplePotionTimeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> {
                    model.getPurpleGuardiansList().forEach(PurpleGuardian::unblockMovementByPotion);
                })
            );
            purplePotionTimeline.setCycleCount(1);
            purplePotionTimeline.play();
            potionsConsumed.put("PURPLE", false);
            model.setPotionsConsumed(potionsConsumed);        
        }
    }

    public void teleportHero(){
        for (Portal portal : model.getPortalList()) {
            portal.triggerAction(model);
        }
    }

    public void ifNextLevel(){
        //SI MANGER TOUTE LES PIECE alors  LVL SUIVANT
        if (model.isNextLevel()) {
            restartGame();
        }
    }
    public void draw(){
        // Dessine le fond et les éléments du jeu
        view.drawBackground();
        view.drawGameElements(model.getElementsList(), model.getImageMap());
        view.drawScore(model.getScore());
    }
    public void collisionWithGuardians(){
        // Vérification de la collision avec un gardien
        if (model.checkCollisionWithGuardians()) {
            System.out.println("CONTROLLER 161");
            model.setGameOver(true);
        }
    }

    public void checkHeroInTower(){
        // Vérification si le héros est dans une tour et déclenche le compteur de la tour
        for (Tower tower : model.getTowerList()) {
            tower.triggerAction(model);
        }
    }
    public void moveGuardians(){
        // Déplacez les gardiens
        for (Guardian guardian : model.getGuardiansList()) {
            guardian.triggerAction(model); // Déplacement aléatoire des gardiens
        }
    }

    public void checkGameOver(){
        // Si le jeu est terminé, affiche "Game Over" et arrête la timeline
        if (model.isGameOver()) {
            view.drawGameOver(model.getScore(), model.getBestScore());
            gameTimeLine.stop();

            stopAllTimelines();
            model.resetScore();
        }

    }
    public void stopRedGuardian(){
        for (RedGuardian redGuardian : model.getRedGuardiansList()) {
            redGuardian.toggleMovement();
        }
    }
    public void stopBlueGuardian(){
        for (BlueGuardian blueGuardian : model.getBlueGuardiansList()) {
            blueGuardian.toggleMovement();
        }
    }
    public void stopPurpleGuardian(){
        for (PurpleGuardian purpleGuardian : model.getPurpleGuardiansList()) {
            purpleGuardian.toggleMovement();
        }
    }
    public void stopOrangeGuardian(){
        for (OrangeGuardian orangeGuardian : model.getOrangeGuardiansList()) {
            orangeGuardian.toggleMovement();
        }
    }

    public void addRandomGuardian() {
        // Génére des coordonnées aléatoires dans la cour
        int randomX = random.nextInt(model.getColumns());
        int randomY = random.nextInt(model.getRows());
    
        // Choisir un type de gardien aléatoirement
        int guardianType = random.nextInt(4); // Il y a 4 types de gardiens
    
        switch (guardianType) {
            case 0:
                model.generateBlueGuardian(randomX, randomY);
                break;
            case 1:
                model.generateOrangeGuardian(randomX, randomY);
                break;
            case 2:
                model.generateRedGuardian(randomX, randomY);
                break;
            case 3:
                model.generatePurpleGuardian(randomX, randomY);
                break;
        }
    }
    public void addRandomPotion(){
        int potionIndex = random.nextInt(4);
        switch (potionIndex) {
            case 0:
                model.generateBluePotions();
                break;
            case 1:
                model.generateRedPotions();
                break;
            case 2:
                model.generatePurplePotions();
                break;
            case 3:
                model.generateOrangePotions();
                break;
        }
    }

    public void handleKeyEvent(KeyEvent event) {
        KeyCode code = event.getCode();
        Hero hero = model.getHero();
    
        if (!model.isGameOver()) {
            if (hero.canMove()) {
                if (code == KeyCode.RIGHT) {
                    hero.moveRight();
                } else if (code == KeyCode.LEFT) {
                    hero.moveLeft();
                } else if (code == KeyCode.UP) {
                    hero.moveUp();
                } else if (code == KeyCode.DOWN) {
                    hero.moveDown();
                }
            } 
            if (code == KeyCode.NUMPAD0) { 
                restartGame();
            } else if (code == KeyCode.NUMPAD4) {
                addRandomPotion();
            } else if (code == KeyCode.NUMPAD5) {
                model.generateCape();
            } else if (code == KeyCode.NUMPAD6) {
                addRandomGuardian();
            } else if (code == KeyCode.R) {
                stopRedGuardian();
            } else if (code == KeyCode.B) {
                stopBlueGuardian();
            } else if (code == KeyCode.M) {
                stopPurpleGuardian();
            } else if (code == KeyCode.O) {
                stopOrangeGuardian();
            } else if (code == KeyCode.S) {
                model.toggleGodMode();
            }
        } else {
            // les touches R et Q pour redémarrer ou quitter
            if (code == KeyCode.R) {
                restartGame(); // redemarre l'app
            } else if (code == KeyCode.Q) {
                Platform.exit();  // Quitte l'application
            }
        } 
    }
}

