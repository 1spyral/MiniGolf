// Helper class used to change states of a StateMachine
public class StateChanger {
    // Switch from starting menu to difficulty select menu
    public static StateLambda startToSelect = (StateMachine stateMachine) -> {
        stateMachine.toggleStart();
        stateMachine.toggleSelect();
    };
    // Switch from difficulty select menu to game menu, and build the golf course
    public static StateLambda selectToGame = (StateMachine stateMachine) -> {
        stateMachine.toggleSelect();
        stateMachine.toggleGame();
        stateMachine.buildGame();
    };
    // Set difficulty to easy
    public static StateLambda easy = (StateMachine stateMachine) -> {
        stateMachine.setDifficulty(1);
        selectToGame.call(stateMachine);
    };
    // Set difficulty to medium
    public static StateLambda medium = (StateMachine stateMachine) -> {
        stateMachine.setDifficulty(2);
        selectToGame.call(stateMachine);
    };
    // Set difficulty to hard
    public static StateLambda hard = (StateMachine stateMachine) -> {
        stateMachine.setDifficulty(3);
        selectToGame.call(stateMachine);
    };
    // Pause/unpause the game
    public static StateLambda pause = (StateMachine stateMachine) -> {
        stateMachine.toggleGame();
        stateMachine.togglePause();
    };
    // Quit the game through the pause menu
    public static StateLambda pauseToStart = (StateMachine stateMachine) -> {
        stateMachine.togglePause();
        stateMachine.toggleStart();
    };
    // Enter/exit the leaderboard menu
    public static StateLambda leaderboard = (StateMachine stateMachine) -> {
        stateMachine.toggleLeaderboard();
        stateMachine.toggleStart();
    };
    // Switch from the win menu to the starting menu
    public static StateLambda winToStart = (StateMachine stateMachine) -> {
        stateMachine.toggleWin();
        stateMachine.toggleStart();
    };
    private StateChanger() {}
}
