public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.Start();
        while (!gameEngine.isVictory()) {
            gameEngine.nextTurn();
        }
        gameEngine.Stop();
    }
}
