package ttt.pattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ttt
 * @date 2023/7/9
 * @project design-pattern
 * 观察者模式：当一个对象的状态发生改变时，已经登记的其他对象能够观察到这一改变。减少了对象之间的依赖关系。
 * 交互对象：主题、具体主题、观察者、具体观察者
 * 使用场景：链式触发机制
 */
public class ObserverDemo {
    public static void main(String[] args){
        Observer observer1 = new Observer1();
        Observer observer2 = new Observer2();
        Subject subject = new Subject();
        subject.attach(observer1);
        subject.attach(observer2);
        Observed observed = new Observed(subject);
        observed.changeState();
    }
}
//抽象的观察者接口
interface Observer{
    public void update();
}
//具体观察者
class Observer1 implements Observer{

    @Override
    public void update() {
        System.out.println("观察者1侦测到改变");
    }
}
//具体观察者
class Observer2 implements Observer{

    @Override
    public void update() {
        System.out.println("观察者2侦测到改变");
    }
}
//主题对象，将观察者和被观察者绑定
class Subject{
    private List<Observer> observerList = new ArrayList<>();//一个主题与n个观察者绑定，发生指定变化时，通知所有观察者
    public void attach(Observer observer){
        this.observerList.add(observer);
    }
    public void notify1(){
        System.out.println("检测到状态变化，开始通知观察者");
        for(Observer observer:observerList){
            observer.update();
        }
    }
}
//被观察对象
class Observed{
    private Subject subject; //一个被观察者对象至少包含一个主题，当发生特定变化时，变化传递至特定主题
    public Observed(Subject subject){
        this.subject = subject;
    }
    public void changeState(){
        System.out.println("状态改变");
        this.subject.notify1();
    }
}


