package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/15
 * @project design-pattern
 * 策略模式：策略模式定义了一系列算法，封装了每个算法，并使它们可以互换。降低了在有多种算法相似的情况下，使用 if...else 所带来的复杂和难以维护性。
 * 交互对象：抽象策略、具体策略
 */
public class StrategyDemo {
    public static void main(String[] args){
        MyContext context1 = new MyContext(new TravelByBike());
        MyContext context2 = new MyContext(new TravelByCar());
        context1.travel();
        context2.travel();
    }
}
//上下文
class MyContext{
    private Travel travelMethod;
    public MyContext(Travel travel){
        this.travelMethod = travel;
    }
    public void travel(){
        this.travelMethod.travel();
    }
}
//抽象策略类
interface Travel{
    public void travel();
}
//具体策略1
class TravelByBike implements Travel{

    @Override
    public void travel() {
        System.out.println("travel by bike");
    }
}
//具体策略2
class TravelByCar implements Travel{

    @Override
    public void travel() {
        System.out.println("travel by car");
    }
}