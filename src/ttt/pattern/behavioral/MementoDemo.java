package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/12
 * @project design-pattern
 * 备忘录模式：保存对象的内部状态，用于在执行某种操作之后可以恢复为原本的状态。
 * 经典案例：只要需要执行回滚操作，都可以使用备忘录模式。可用于各种原子事务，如果其中一个操作失败，必须将对象恢复到初始状态。
 * 交互对象：需要保存状态的对象、备忘录
 */
public class MementoDemo {
    public static void main(String[] args){
        Car car = new Car();
        //执行操作前先保存初始状态
        Car.Memento carMemento = car.saveState();
        car.setState("state1");
        car.setState("state2");
        //恢复状态为初始状态
        car.restoreState(carMemento);
        System.out.println(car.getState());
    }
}

class Car{
    private String state = "init state";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    //用于记录状态的方法
    public Memento saveState(){
        return new Memento(this.state);
    }
    //用于从备忘录恢复状态的方法
    public void restoreState(Memento memento){
        this.state = memento.state;
    }
    //备忘录对象：由于需要访问对象的内部成员，故通常设计为内嵌类
    public class Memento{
        private String state;
        public Memento(String state){
            this.state = state;
        }
    }
}
