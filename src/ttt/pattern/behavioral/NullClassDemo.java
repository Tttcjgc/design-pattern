package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/17
 * @project design-pattern
 * 空对象模式：使用一个空对象取代null，在数据不可用时提供默认行为而不是仅仅返回null（使用空对象一定程度上可以避免空指针异常）
 */
public class NullClassDemo {
    public static void main(String[] args){
        AbstractPeople ap1 = PeopleFactory.createPeople("ttt");
        AbstractPeople ap2 = PeopleFactory.createPeople("");
        System.out.println(ap1.getName());
        System.out.println(ap2.getName());
    }
}

abstract class AbstractPeople{
    String name;
    public abstract String getName();
}
//具体存在的类，代表一般情况
class RealPeople extends AbstractPeople{
    String name;
    public RealPeople(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }
}
//空对象所属的类，用于特殊情况的兜底
class NullPeople extends AbstractPeople{
    String name;
    @Override
    public String getName() {
        return "null people";
    }
}

class PeopleFactory{
    public static AbstractPeople createPeople(String name){
        if(!"".equals(name)){
            return new RealPeople(name);
        }
        //当未传入参数时，创建并返回一个空对象而不是null
        return new NullPeople();
    }
}
