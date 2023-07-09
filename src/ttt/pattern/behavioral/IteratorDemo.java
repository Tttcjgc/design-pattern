package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/8
 * @project design-pattern
 * 迭代器模式：迭代器提供了一种遍历聚合对象元素而不暴露其内部结构的方法。
 * 交互对象：抽象容器、抽象迭代器、具体容器、具体迭代器
 * 经典案例：java中的容器类
 */
public class IteratorDemo {
    public static void main(String[] args){
        String[] arr = {"111","222","333"};
        MyList myList = new MyList(arr);
        MyIterator iterator = myList.getIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
//抽象容器
interface MyCollection{

}
//抽象迭代器
interface MyIterator{
    public Object next();
    public boolean hasNext();
}
//具体容器
class MyList implements MyCollection{
    private String[] values;
    public MyList(String[] arr){
        this.values = arr;
    }
    //容器内部通常会包含一个用于获取迭代器的方法
    public MyCollectionIterator getIterator(){
        return new MyCollectionIterator();
    }
    //由于迭代器需要访问容器的成员，所以在容器内实现迭代器是最好的选择
    private class MyCollectionIterator implements MyIterator{

        private int position;
        @Override
        public Object next() {
            if(hasNext()){
                return values[position++];
            }else{
                return null;
            }
        }

        @Override
        public boolean hasNext() {
            return position<values.length;
        }
    }
}
