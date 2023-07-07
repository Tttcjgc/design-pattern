package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/5
 * @project design-pattern
 * 命令模式：将请求封装到一个命令对象中，传给命令调用对象，命令调用对象再根据具体的命令执行代码。解除了行为请求者与行为实现者的紧耦合关系。
 * 交互对象：命令调用者，抽象的命令接口，具体的命令类
 * 典型案例：实现行为的撤销\重做（需要搭配命令队列）
 */
public class CommandDemo {
    public static void main(String[] args){
       CommandInvoker cv = new CommandInvoker();
       cv.invoke(new DrawCycleCommand());
       cv.invoke(new DrawTriangleCommand());
    }
}
//抽象的命令类，可根据需要随意添加具体的实现类，无论如何添加，原有代码均不需修改
interface Command{
    void execute();
}
//具体命令类
class DrawCycleCommand implements Command{
    @Override
    public void execute() {
        System.out.println("画圆");
    }
}
//具体命令类
class DrawTriangleCommand implements Command{
    @Override
    public void execute() {
        System.out.println("画三角形");
    }
}
//命令调度者
class CommandInvoker{
    public void invoke(Command command){
        command.execute();
    }
}
