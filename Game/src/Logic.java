import java.util.Arrays;
import java.util.List;

public class Logic {
    private int score = 0;
    public int maximum = 0;
    private int count = 0;
    private static int[][] data;
    private int goal;
    private static final List<Integer> POSSIBLE_GOALS = Arrays.asList(8, 16, 32, 64, 128, 256, 512, 1024, 2048);


    public Logic() {
        data = new int[4][4];
        goal = 16;
    }

    public void random() {
        boolean added = true;
        if (checkForNumbers()) {
            while (added) {
                int randomElement1 = (int) (0 + Math.random() * 4);
                int randomElement2 = (int) (0 + Math.random() * 4);
                if (data[randomElement1][randomElement2] == 0) {
                    if (count % 5 == 0) {
                        data[randomElement1][randomElement2] = 4;
                    } else {
                        data[randomElement1][randomElement2] = 2;
                    }
                    added = false;
                }
            }
        }
        count++;
    }

    private boolean checkForNumbers() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (data[r][c] == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public void moveUp() {
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                for (int i = r; i > 0; i--) {
                    if (data[i - 1][c] == 0) {
                        data[i - 1][c] = data[i][c];
                        data[i][c] = 0;
                    }
                }
            }
            for (int j = 0; j < 3; j++) {
                if (data[j][c] != 0 && data[j][c] == data[j + 1][c]) {
                    data[j][c] *= 2;
                    score += data[j][c];
                    for (int k = j + 1; k < 3; k++) {
                        data[k][c] = data[k + 1][c];
                    }
                    data[3][c] = 0;
                }
            }
        }
    }

    public void moveLeft() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int i = c; i > 0; i--) {
                    if (data[r][i - 1] == 0) {
                        data[r][i - 1] = data[r][i];
                        data[r][i] = 0;
                    }
                }
            }
            for (int j = 0; j < 3; j++) {
                if (data[r][j] != 0 && data[r][j] == data[r][j + 1]) {
                    data[r][j] *= 2;
                    score += data[r][j];
                    for (int k = j + 1; k < 3; k++) {
                        data[r][k] = data[r][k + 1];
                    }
                    data[r][3] = 0;
                }
            }
        }
    }

    public void moveRight() {
        for (int r = 0; r < 4; r++) {
            for (int c = 3; c >= 0; c--) {
                for (int i = c; i < 3; i++) {
                    if (data[r][i + 1] == 0) {
                        data[r][i + 1] = data[r][i];
                        data[r][i] = 0;
                    }
                }
            }
            for (int j = 3; j > 0; j--) {
                if (data[r][j] != 0 && data[r][j] == data[r][j - 1]) {
                    data[r][j] *= 2;
                    score += data[r][j];
                    for (int k = j - 1; k > 0; k--) {
                        data[r][k] = data[r][k - 1];
                    }
                    data[r][0] = 0;
                }
            }
        }
    }

    public void moveDown() {
        for (int c = 0; c < 4; c++) {
            for (int r = 3; r >= 0; r--) {
                for (int i = r; i < 3; i++) {
                    if (data[i + 1][c] == 0) {
                        data[i + 1][c] = data[i][c];
                        data[i][c] = 0;
                    }
                }
            }
            for (int j = 3; j > 0; j--) {
                if (data[j][c] != 0 && data[j][c] == data[j - 1][c]) {
                    data[j][c] *= 2;
                    score += data[j][c];
                    for (int k = j - 1; k > 0; k--) {
                        data[k][c] = data[k - 1][c];
                    }
                    data[0][c] = 0;
                }
            }
        }
    }

    public int getGoal() {
        return goal;
    }

    public void initGame() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                data[r][c] = 0;
            }
        }
        int randomElement1 = (int) (0 + Math.random() * 4);
        int randomElement2 = (int) (0 + Math.random() * 4);
        data[randomElement1][randomElement2] = 2;
        for (int r = 0; r < 4; r++) {
            boolean added = true;
            for (int c = 0; c < 4; c++) {
                if (data[r][c] == 0) {
                    randomElement1 = (int) (0 + Math.random() * 4);
                    randomElement2 = (int) (0 + Math.random() * 4);
                    added = false;
                    break;
                }
            }
            if (!added) {
                break;
            }
        }
        data[randomElement1][randomElement2] = 2;
        count++;
        score = 0;
        maximum = 0;
    }

    public boolean checkForGoal() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (data[r][c] > maximum) {
                    maximum = data[r][c];
                }
            }
        }
        return maximum == goal;
    }

    public int getValue(int r, int c) {
        return data[r][c];
    }

    public void changeGoalMinus() {
        int index = POSSIBLE_GOALS.indexOf(goal);
        if (goal > 8) {
            index--;
            goal = POSSIBLE_GOALS.get(index);
            initGame();
        }
    }

    public void changeGoalPlus() {
        int index = POSSIBLE_GOALS.indexOf(goal);
        if (goal < 2048) {
            index++;
            goal = POSSIBLE_GOALS.get(index);
            initGame();
        }
    }


    public int getScore() {
        return score;
    }

    public boolean checkForLost() {
        boolean up = false;
        boolean right = false;
        for (int r = 0; r < 4; r++) {
            for (int c = 3; c > 0; c--) {
                if (data[r][c] == data[r][c - 1]) {
                    right = true;
                    break;
                }
            }
        }
        for (int c = 0; c < 4; c++) {
            for (int r = 3; r > 0; r--) {
                if (data[r - 1][c] == data[r][c]) {
                    up = true;
                    break;
                }
            }
        }
        return !right && !up && !checkForNumbers();
    }
}