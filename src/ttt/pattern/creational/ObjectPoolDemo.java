package ttt.pattern.creational;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ttt
 * @date 2023/7/3
 * @project design-pattern
 * 对象池模式：重用并共享那些造价高昂的对象，将其事先创建好并放入一个对象池中，需要使用时直接从池中取。最典型的比如数据库连接池、redis连接池等。
 * 交互对象：资源池类、资源类
 */
public class ObjectPoolDemo {
    public static void main(String[] args) throws Exception {
        ConnectionPool pool = new ConnectionPool().init(10);
        for(int i=0;i<15;i++){
            System.out.println(i);
            pool.getConnection();
        }
    }
}

class Connection{
    public Connection(){
    }
}
//还没写完
class ConnectionPool{
    private int max;
    private int connectionNum = 0;
    private Queue<Connection> queue;
    public ConnectionPool init(int max){
        this.queue = new ArrayDeque<Connection>();
        this.max = max;
        while(this.connectionNum++ < this.max){
            queue.offer(new Connection());
        }
        return this;
    }
    public Connection getConnection() throws Exception {
        if(connectionNum==0){
            throw new Exception("连接池资源耗尽");
        }else{
            connectionNum--;
            return queue.poll();
        }
    }
}
