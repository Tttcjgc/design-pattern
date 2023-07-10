package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/10
 * @project design-pattern
 * 中介者模式：定义了一个对象，该对象封装了一组对象的交互方式，从而减少了他们之间的直接依赖。当有许多实体以相似的方式进行交互时应该考虑使用中介者模式。
 * 经典案例：通信应用程序、Timer
 * 交互对象：抽象中介者、具体中介者、抽象同事角色、具体同事角色
 */
public class MediatorDemo {
    public static void main(String[] args){
        User user1 = new User("ttt");
        User user2 = new User("hhh");
        user1.send(user2,"666666");
    }
}

class ChatRoom{
    public static void sendMessage(User src,User des,String message){
        des.get(src,message);
    }
}

class User{
    String name;
    public User(String name){
        this.name = name;
    }
    public void send(User user,String message){
        ChatRoom.sendMessage(this,user,message);
    }
    public void get(User user,String message){
        System.out.println(this.name+" get \""+message+"\" from "+user.name);
    }

}


