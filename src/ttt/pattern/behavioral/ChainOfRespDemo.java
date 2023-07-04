package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/4
 * @project design-pattern
 * 责任链模式：当需要对一批从客户端来的请求执行多种不同的操作时，用多种类各自负责不同的任务，而不是将所有任务集成到一个类中，能有效降低系统的耦合度，提高代码可读性。
 * 交互对象：抽象处理器处理器（即责任链中的节点）、实现了抽象处理器的具体处理器。
 *      请求到达一个处理器时，该处理器会先判断是否为自己的责任，若是，则处理请求并返回，否则将其发送给后继处理器。
 * 典型例子：web中用来针对不同url过滤请求的过滤器。
 */
public class ChainOfRespDemo {
    public static void main(String[] args){
        Filter filterA = new FilterA();
        Filter filterB = new FilterB();
        ((FilterA) filterA).next = filterB;
        filterA.getRequest("A");
        filterA.getRequest("B");
    }
}
//抽象的处理器，提供具体处理器的模板
interface Filter{
    public void getRequest(String req);
    public void handleRequest();
}
//具体处理器A
class FilterA implements Filter{
    public Filter next;
    @Override
    public void getRequest(String req) {  //模拟过滤器接收请求
        if("A".equals(req)){    //判断是否为自身职责
            handleRequest();//具体的处理逻辑
        }else{
            if(this.next!=null){
                this.next.getRequest(req);//非自身职责，丢给下一个处理器
            }
        }
    }

    @Override
    public void handleRequest() {
        System.out.println("A");
    }
}
//具体处理器B
class FilterB implements Filter{
    public Filter next;
    @Override
    public void getRequest(String req) {
        if("B".equals(req)){
            handleRequest();
        }else{
            if(this.next!=null){
                this.next.getRequest(req);
            }
        }
    }

    @Override
    public void handleRequest() {
        System.out.println("B");
    }
}
