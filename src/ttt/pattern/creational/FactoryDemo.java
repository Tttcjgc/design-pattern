package ttt.pattern.creational;

/**
 * @author ttt
 * @date 2023/7/2
 * @project design-pattern
 * 工厂模式：增加工厂类专门用来实例化对象，打破主类与具体实例类的耦合。分为简单工厂模式和抽象工厂模式(比如仅创建单一类型的工厂方法模式)。
 */
public class FactoryDemo {

    public static void main(String[] args){
        //简单工厂类
        SimpleWeaponFactory.createWeapon(SimpleWeaponFactory.WeaponType.Knife);
        SimpleWeaponFactory.createWeapon(SimpleWeaponFactory.WeaponType.Bow);
        //基于反射的简单工程类
        ReflectWeaponFactory.createWeapon("ttt.pattern.creational.Gun");
        //抽象工厂类
        AbstractWeaponFactory bowFactory = new BowFactory();
        bowFactory.createWeapon("ttt");
        AbstractWeaponFactory gunFactory = new GunFactory();
        gunFactory.createWeapon("hhh");
    }
}

abstract class Weapon{
}

class Bow extends Weapon{
    private String brand;
    public Bow(){
        System.out.println("get a bow");
    }
    public Bow(String brand){
        System.out.println("get a bow made by "+brand);
    }
}

class Knife extends Weapon{
    public Knife(){
        System.out.println("get a knife");
    }
}

class Gun extends Weapon{
    private String brand;
    public Gun(){
        System.out.println("get a gun");
    }
    public Gun(String brand){
        System.out.println("get a gun made by "+brand);
    }
}

//简单工厂模式，需要增加新的Weapon时，必须修改代码，不符合开闭原则
class SimpleWeaponFactory{
    public enum WeaponType{
        Bow,Knife;
    }
    
    public static Weapon createWeapon(WeaponType type){
        Weapon weapon = null;
        if(type.equals(WeaponType.Bow)){
            weapon = new Bow();
        }
        if(type.equals(WeaponType.Knife)){
            weapon = new Knife();
        }
        return weapon;
    }
}

//使用反射机制创建对象的简单工厂模式，新增weapon时无需修改代码，符合开闭原则
//缺点是反射会影响实例创建的效率
class ReflectWeaponFactory{
    public static Weapon createWeapon(String weaponClass){
        Weapon weapon = null;
        try {
            Class<?> clazz = Class.forName(weaponClass);
            weapon = (Weapon) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("无法创建指定的武器，原因是武器不存在");
        }
        return weapon;
    }
}


//抽象工厂模式，需要新增Weapon类型的时候派生具体的具体工厂类即可，无需修改任何代码即可实现扩展
//此处，该抽象工厂仅生产一类产品，故也为工厂方法模式
abstract class AbstractWeaponFactory{
    protected abstract Weapon createWeapon(String brand);
    public Weapon getWeapon(String brand){
        return createWeapon(brand);
    }
}
//抽象工厂类的具体实现类之一
class BowFactory extends AbstractWeaponFactory{
    @Override
    protected Weapon createWeapon(String brand) {
        return new Bow(brand);
    }
}
//抽象工厂类的具体实现类之二
class GunFactory extends AbstractWeaponFactory{
    @Override
    protected Weapon createWeapon(String brand) {
        return new Gun(brand);
    }
}

