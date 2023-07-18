package ttt.pattern.behavioral;

/**
 * @author ttt
 * @date 2023/7/18
 * @project design-pattern
 * 访问者模式：使用了一个访问者类，它改变了被访问类的执行算法（在被访问的类里面加一个对外提供接待访问者的接口）。通过这种方式，被访问的执行算法可以随着访问者改变而改变。
 *
 */
public class VisitorDemo {
    public static void main(String[] args){
        new Keyboard().accept(new ComputerPartDisplayVisitor());
        new Monitor().accept(new ComputerPartDisplayVisitor());
    }
}

interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}

class Keyboard  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Monitor  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

interface ComputerPartVisitor {
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}

class ComputerPartDisplayVisitor implements ComputerPartVisitor {

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor.");
    }
}
