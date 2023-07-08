package ttt.pattern.behavioral;

import java.util.Stack;

/**
 * @author ttt
 * @date 2023/7/8
 * @project design-pattern
 * 解释器模式：通过将表达式表示为内部表示来解析表达式。解释器定义了某种语法对应的解释方法。
 * 交互对象：抽象表达式、上下文环境、终结符表达式、非终结符表达式
 * 经典案例：四则运算器（本例中为后缀表达式）
 */
public class InterpreterDemo {
    public static void main(String[] args){
        Evaluator eva = new Evaluator();
        System.out.println(eva.evaluate("2 4 + 6 -"));  //计算2+4-6
        System.out.println(eva.evaluate("33 45 + 64 - 11 + 7 -"));  //计算33+45-64+11-7
    }
}
//抽象的表达式类，定义通用的接口
abstract class Expression{
    abstract public int interpret();
}
//终结符表达式，用于解释所有数字
class Number extends Expression{

    private int number;
    public Number(int number){
        this.number = number;
    }
    @Override
    public int interpret() {
        return this.number;
    }
}
//非终结符表达式：加法。
class Plus extends Expression{

    Expression left;
    Expression right;
    public Plus(Expression left,Expression right){
        this.left = left;
        this.right = right;
    }
    @Override
    public int interpret() {
        return this.left.interpret() + this.right.interpret();
    }
}
//非终结符表达式：减法。
class Minus extends Expression{

    Expression left;
    Expression right;
    public Minus(Expression left,Expression right){
        this.left = left;
        this.right = right;
    }
    @Override
    public int interpret() {
        return this.right.interpret() - this.left.interpret();  //考虑到pop的顺序，这里需要是右值减左值
    }
}
//用于构建语法树、执行表达式计算的类
class Evaluator{

    public int evaluate(String expression){
        Stack<Expression> stack = new Stack<Expression>();
        int result = 0;
        for (String token:expression.split(" ")){
            if(isNumber(token)){
                stack.push(new Number(Integer.parseInt(token)));
            }else if(isOperator(token)){
                Expression exp = null;
                if("+".equals(token)){
                    exp = new Plus(stack.pop(), stack.pop());
                }else if("-".equals(token)){
                    exp = new Minus(stack.pop(), stack.pop());
                }
                result = exp.interpret();
                stack.push(new Number(result));
            }
        }
        return result;
    }

    private boolean isNumber(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean isOperator(String str){
        return ("+".equals(str)||"-".equals(str));
    }

}
