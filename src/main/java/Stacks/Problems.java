package Stacks;

import java.util.Stack;

public class Problems {

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static boolean isParenthesis(char ch) {
        return ch == '(';
    }

    private static boolean isClosingParenthesis(char ch) {
        return ch == ')';
    }


    private static boolean hasHigherPrecdence(char op, char top){
        if(op=='+' || op=='-'){
            return (top=='*' || top=='/');
        }
        return false;
    }

    public static String infixToPostfix(String str) {
        Stack<Character> stack = new Stack<>();
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(isParenthesis(ch)) stack.push(ch);
            else if (isOperator(ch)){
                if(!stack.isEmpty() && hasHigherPrecdence(ch,stack.peek())){
                    while(!stack.isEmpty() && stack.peek()!='('){
                        result += stack.pop();
                    }
                } 
                stack.push(ch);
            }
            else if(isClosingParenthesis(ch)){
                while(!stack.isEmpty()){
                    char op = stack.pop();
                    if(op=='(') break;
                    result += op;
                }
            }
            else result += ch;
        }

        while(!stack.isEmpty()) result += stack.pop();

        return result;
    }

    private static int calculate(int op1,int op2, char op){
        switch(op){
            case '+':
                return op1+op2;
            case '-':
                return op1-op2;
            case '*':
                return op1*op2;
            case '/':
                return op1/op2;
        }

        return 0;
    }

    public static int evaulateInfix(String str){
        Stack<Character> operators = new Stack<>();
        Stack<Integer> operands = new Stack<>();

        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(isOperator(ch)){
                operators.push(ch);
            } else{
                operands.push(Character.getNumericValue(ch));

                if(!operators.isEmpty() && (operators.peek()=='*' || operators.peek()=='/')){
                    int op1 = operands.pop();
                    int op2 = operands.pop();
                    operands.push(calculate(op1,op2,operators.pop()));
                }
            }
        }

        while(!operators.isEmpty()){
            int op1 = operands.pop();
            int op2 = operands.pop();
            operands.push(calculate(op2,op1,operators.pop()));
        }

        return operands.pop();
    }
}
