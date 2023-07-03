package ttt.pattern.creational;

/**
 * @author ttt
 * @date 2023/7/3
 * @project design-pattern
 * 原型模式：实际上是一种克隆对象的方法，了解浅拷贝和深拷贝即可。
 * 交互对象：原型类、具体类
 */
public class PrototypeDemo {
    
    public static void main(String[] args){
        Man man1 = new Man();
        Woman w1 = new Woman();
        man1.name = "manA";
        man1.phone = new Phone("apple",7777.7);
        w1.name = "wA";
        w1.phone = new Phone("xiaomi",2222.22);

        Man man2 = man1.clone();
        Woman w2 = w1.clone();
        System.out.println(man1.phone+" vs "+man2.phone);  //浅拷贝，两个人的手机相同
        System.out.println(w1.phone+" vs "+w2.phone); //深拷贝，两个人手机不同
    }
    
}

class Phone{
    String brand;
    double price;
    public Phone(String brand,double price){
        this.price = price;
        this.brand = brand;
    }
}
//原型类，提供clone方法供具体类实现
abstract class HumanProto{
    String name;
    Phone phone;
    abstract public HumanProto clone();
}

//具体类，采用浅拷贝
class Man extends HumanProto{
    @Override
    public Man clone() {
        Man man = new Man();
        man.name = this.name;
        man.phone = this.phone;
        return man;
    }
}
//具体类，采用深拷贝
class Woman extends HumanProto{
    @Override
    public Woman clone() {
        Woman woman = new Woman();
        woman.name = this.name;
        woman.phone = new Phone(this.phone.brand,this.phone.price);
        return woman;
    }
}
