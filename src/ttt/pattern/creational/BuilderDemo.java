package ttt.pattern.creational;

/**
 * @author ttt
 * @date 2023/7/2
 * @project design-pattern
 * 建造者模式：当一个对象的结构相对复杂，实例化逻辑比较多时，将实例化放到构造函数中会使该类变得体量庞大，最好将实例化过程抽离，由建造者来进行
 * 和工厂的区别在于工厂不干涉类实例化的具体逻辑
 * 本例中为了方便，仅为Car类添加了两个属性，实际上用到建造者的地方通常不会这么简单
 */
public class BuilderDemo {
    public static void main(String[] args){
        CarBuilder eletricCarBuilder = new EletricCarBuilder();
        eletricCarBuilder.buildCar("batteries","none");
        CarBuilder gasolineCarBuilder = new GasolineCarBuilder();
        gasolineCarBuilder.buildCar("batteries","none");
    }
}

class Car{
    String batteries;
    String gasTank;
}

class GasolineCar extends Car{
    public GasolineCar(){
        System.out.println("a gasoline car");
    }
}

class EletricCar extends Car{
    public EletricCar(){
        System.out.println("a electric car");
    }
}

//抽象建造者类，提高扩展性
abstract class CarBuilder{
    abstract public Car buildCar(String batteries,String gasTank);
}
//具体的建造者，封装了具体的构造逻辑
class GasolineCarBuilder extends CarBuilder{
    @Override
    public GasolineCar buildCar(String batteries, String gasTank) {
        GasolineCar gasolineCar = new GasolineCar();
        gasolineCar.batteries = batteries;
        gasolineCar.gasTank = gasTank;
        return gasolineCar;
    }
}
//具体的建造者，封装了具体的构造逻辑
class EletricCarBuilder extends CarBuilder{
    @Override
    public EletricCar buildCar(String batteries, String gasTank) {
        EletricCar eletricCar = new EletricCar();
        eletricCar.batteries = batteries;
        eletricCar.gasTank = gasTank;
        return eletricCar;
    }
}
