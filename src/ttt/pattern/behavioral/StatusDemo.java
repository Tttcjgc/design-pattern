package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/14
 * @project design-pattern
 * 状态模式：类似科学计算领域的有限状态机，类的行为是基于它的状态改变的。
 * 交互对象：抽象状态类、具体状态类、随状态改变的类
 * 经典案例：有限状态机
 */
public class StatusDemo {
    public static void main(String[] args){
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
//抽象状态类
interface State {
    public void doAction(Context context);
}
//具体状态类1
class StartState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}
//具体状态类2
class StopState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString(){
        return "Stop State";
    }
}
//行为类
class Context {
    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
