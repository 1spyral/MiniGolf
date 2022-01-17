import java.beans.Statement;

public class StateChanger {
    public static StateLambda startToSelect = (StateMachine stateMachine) -> {
        stateMachine.toggleStart();
        stateMachine.toggleSelect();
    };
    public static StateLambda selectToGame = (StateMachine stateMachine) -> {
        stateMachine.toggleSelect();
        stateMachine.toggleGame();
    };
    public static StateLambda easy = (StateMachine stateMachine) -> {
        stateMachine.setDifficulty(1);
        selectToGame.call(stateMachine);
    };
    public static StateLambda medium = (StateMachine stateMachine) -> {
        stateMachine.setDifficulty(2);
        selectToGame.call(stateMachine);
    };
    public static StateLambda hard = (StateMachine stateMachine) -> {
        stateMachine.setDifficulty(3);
        selectToGame.call(stateMachine);
    };
    private StateChanger() {}
}
