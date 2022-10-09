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
    
    public static int largestRectangleBF(int[] heights){
        int maxArea = 0;
        
        for (int i = 0; i < heights.length; i++) {
            int currArea = 0;
            int curr = heights[i];
            
            int j = i;
            int k = i;
            
            while (j > 0 && heights[j - 1] >= curr) {
                j--;
            }
            while (k < heights.length - 1 && heights[k + 1] >= curr) {
                k++;
            }
            
            currArea = (k - j + 1) == 0 ? heights[i] : (k - j + 1) * curr;
            
            maxArea = Math.max(currArea, maxArea);
        }
        
        return maxArea;
    }
    
    public static int largestRectangle(int[] heights){
        int i=0,n=heights.length, area=0;
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        
        while(i<n){
            
            while(stack.peek()!=-1 && heights[stack.peek()]>=heights[i]){
                int top = stack.pop();
                int height = heights[top];
                int width = i-stack.peek()-1;
                area = Math.max(area, height*width);
            }
            
            stack.push(i++);
        }
        
        while (stack.peek() != -1) {
            int top = stack.pop();
            int height = heights[top];
            int width = i - stack.peek() - 1;
            area = Math.max(area, height * width);
        }
        
        
        return area;
    }
}
