import java.beans.Statement;

public class StateChanger {
    public static StateLambda startToSelect = (StateMachine stateMachine) -> {
        stateMachine.toggleStart();
        stateMachine.toggleSelect();
    };
    private StateChanger() {}
}
