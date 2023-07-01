package ttt.pattern.creational;

/**
 * @author ttt
 * @date 2023/7/1
 * @project design-pattern 
 * 单例模式：一个对象只能创建一个实例，如各种配置类、工具类、业务逻辑类、前端控制器等
 */


public class SingletonDemo {
    public static void main(String[] args) throws Exception {
        Class.forName("ttt.pattern.creational.Earth");
        Class.forName("ttt.pattern.creational.LazyEarth");
        Thread.sleep(5000); //可以看到，在sleep之前，Earth已经生成了实例，而LazyEarth实例还未生成
        // new Earth();  单例对象绝对不允许程序在任何位置通过new来创建实例
        Earth earth1 = Earth.getEarth();
        Earth earth2 = Earth.getEarth();
        if(earth1==earth2){
            System.out.println("同一个地球");
        }
        //直到此处，LazyEarth都不会生成
        LazyEarth lazyEarth1 = LazyEarth.getLazyEarth();
        LazyEarth lazyEarth2 = LazyEarth.getLazyEarth();
        if(lazyEarth1==lazyEarth2){
            System.out.println("同一个懒地球");
        }
    }
}

//预加载模式的单例类，类加载的时候就生成实例，线程安全，无需使用同步机制
class Earth{
    //类加载时即生成Earth实例，初始化该变量，即为预加载模式
    private static Earth earth = new Earth();
    //单例类的特点：私有的构造方法
    private Earth(){
        System.out.println("Earth 被创建了...");
    }
    //对外暴露的接口，用以获取该类的实例
    public static Earth getEarth() {
        return earth;
    }
}

//懒加载模式的单例类，需要使用实例的时候才生成实例，线程不安全，多线程情况下需要同步锁来控制实例化过程
class LazyEarth{
    //类加载时不生成实例
    private static LazyEarth lazyEarth;

    private LazyEarth(){
        System.out.println("懒加载地球被创建了");
    }

    public static LazyEarth getLazyEarth(){
        if(lazyEarth==null){
            lazyEarth = new LazyEarth();
        }
        return lazyEarth;
    }
}

//进阶：带同步锁的懒加载单例模式.  ps: 当一个单例类实例化所需时间较长时，同步锁很有必要
//当然，可以直接在方法的声明上添加synchronized来实现线程安全，但是该方案通常会导致方法的效率变低，此处采用更细粒度的方案
class SafeLazyEarth{
    private static SafeLazyEarth safeLazyEarth;

    private SafeLazyEarth(){
        System.out.println("线程安全的懒加载地球被创建了");
    }

    public static SafeLazyEarth getSafeLazyEarth(){
        if(safeLazyEarth==null){
            synchronized (SafeLazyEarth.class){
                if(safeLazyEarth==null) {   //此处避免了"A线程在等锁的时间内B线程创建完实例，导致A线程获得锁后重复创建实例"的情况
                    safeLazyEarth = new SafeLazyEarth();
                }
            }
        }
        return safeLazyEarth;
    }
}
