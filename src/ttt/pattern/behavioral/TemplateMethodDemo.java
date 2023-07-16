package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/16
 * @project design-pattern
 * 模板方法模式：一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。即抽象类定义骨架，子类实现具体步骤。
 *
 */
public class TemplateMethodDemo {
    public static void main(String[] args){
        Game basketball = new BasketBall();
        Game football = new Football();
        basketball.playGame();
        football.playGame();
    }
}
//抽象的类，提供了方法的模板。本例中为playGame()
abstract class Game{
    abstract void init();
    abstract void play();
    abstract void end();
    //模板方法，定义了骨架，通常为不可被覆盖，防止子类修改方法的执行流程
    final void playGame(){
        //子类覆盖的方法将在父类中调用
        init();
        play();
        end();
    }
}
//具体实现类一
class BasketBall extends Game{

    @Override
    void init() {
        System.out.println("篮球比赛准备");
    }

    @Override
    void play() {
        System.out.println("篮球比赛开始");
    }

    @Override
    void end() {
        System.out.println("篮球比赛结束");
    }
}
//具体实现类二
class Football extends Game{

    @Override
    void init() {
        System.out.println("足球比赛准备");
    }

    @Override
    void play() {
     System.out.println("足球比赛开始");
    }

    @Override
    void end() {
        System.out.println("足球比赛结束");
    }
}
