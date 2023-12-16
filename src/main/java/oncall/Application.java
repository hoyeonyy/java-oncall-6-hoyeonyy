package oncall;

import oncall.controller.GameController;

public class Application {
    public static void main(String[] args) {
        new GameController().startGame();
    }
}