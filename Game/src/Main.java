import processing.core.*;

public class Main extends PApplet {
    public static float x;
    public static float y;
    public static float tileSize;
    Logic game;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        x = width / 2f - 300;
        y = height / 2f - 300;
        tileSize = 150;
        game = new Logic();
        game.initGame();
    }

    public void draw() {
        background(0, 0, 0);
        noStroke();
        drawBoard();
    }

    private void setGoal() {
        fill(255, 255, 0);
        textSize(80);
        strokeWeight(2);
        text("Goal:", width / 11f, (height / 2f));
        fill(255, 255, 255);
        text(game.getGoal(), width / 5f, height / 2f);
    }

    public void keyPressed() {
        switch (keyCode) {
            case UP:
                game.moveUp();
                game.random();
                break;
            case DOWN:
                game.moveDown();
                game.random();
                break;
            case RIGHT:
                game.moveRight();
                game.random();
                break;
            case LEFT:
                game.moveLeft();
                game.random();
                break;
        }
        if (key == '+') {
            game.changeGoalPlus();
        }
        if (key == '-') {
            game.changeGoalMinus();
        }
    }

    private void drawBoard() {
        setManual();
        setScore();
        setBest();
        setText();
        setGoal();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                checkForColor(r, c);
                drawTile(r, c);
                fill(255, 50, 255);
                textSize(100);
                if (game.getValue(r, c) != 0) {
                    placeNumber(r, c);
                }
            }
        }
        if (game.checkForGoal()) {
            drawWinTable();
        }
        if (game.checkForLost()) {
            drawLoseTable();
        }
    }

    private void drawWinTable() {
        if (key != ENTER) {
            pushMatrix();
            translate(x, y + 200);
            noStroke();
            fill(20, 20, 150);
            rect(0, 0, 600, 200);
            fill(255, 255, 0);
            textSize(50);
            text("You Reached The Goal: ", 25, 50);
            text("Press enter to continue", 25, 150);
            popMatrix();
        } else {
            game.maximum = 10000;
        }
    }

    private void drawLoseTable() {
        pushMatrix();
        translate(x, y + 200);
        noStroke();
        fill(20, 20, 150);
        rect(0, 0, 600, 200);
        fill(255, 255, 0);
        textSize(50);
        text("You Lost. No moves left: ", 25, 50);
        text("Press enter to restart", 25, 150);
        if (key == ENTER) {
            game.initGame();
        }
        popMatrix();
    }

    private void setManual() {
        fill(255, 255, 0);
        textSize(50);
        strokeWeight(2);
        text("Change Goal: +,-", width / 2.5f, height - 50);
    }

    private void setScore() {
        fill(255, 255, 0);
        textSize(60);
        strokeWeight(2);
        text("Score: " + game.getScore(), x + 650, (height / 2f) - 100);
    }

    private void setBest() {
        fill(255, 255, 0);
        textSize(60);
        strokeWeight(2);
        text("Best: " + game.getScore(), x + 650, (height / 2f) + 100);
    }

    private void placeNumber(int r, int c) {
        switch (game.getValue(r, c)) {
            case 2:
            case 4:
            case 8:
                text(game.getValue(r, c), x + c * tileSize + tileSize / 2 - 25, y + r * tileSize + tileSize / 2 + 25);
                break;
            case 16:
            case 32:
            case 64:
                text(game.getValue(r, c), x + c * tileSize + tileSize / 2 - 50, y + r * tileSize + tileSize / 2 + 25);
                break;
            case 128:
            case 256:
            case 512:
                textSize(80);
                text(game.getValue(r, c), x + c * tileSize + tileSize / 2 - 60, y + r * tileSize + tileSize / 2 + 25);
                break;
            default:
                textSize(60);
                text(game.getValue(r, c), x + c * tileSize + tileSize / 2 - 60, y + r * tileSize + tileSize / 2 + 20);
        }
    }

    private void drawTile(int r, int c) {
        stroke(150);
        strokeWeight(8);
        square(x + tileSize * c, y + r * tileSize, tileSize);
    }

    private void checkForColor(int r, int c) {
        switch (game.getValue(r, c)) {
            case 2 -> fill(238, 228, 218);
            case 4 -> fill(237, 224, 200);
            case 8 -> fill(242, 177, 121);
            case 16 -> fill(245, 149, 99);
            case 32 -> fill(246, 124, 95);
            case 64 -> fill(246, 94, 59);
            case 128 -> fill(237, 207, 114);
            case 256 -> fill(237, 204, 97);
            case 512 -> fill(237, 200, 80);
            case 1024 -> fill(237, 197, 63);
            case 2048 -> fill(237, 194, 46);
            default -> fill(204, 192, 179);
        }
    }

    public void setText() {
        fill(255, 255, 0);
        textSize(100);
        strokeWeight(2);
        text("Game2048", x + 100, 100);
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}