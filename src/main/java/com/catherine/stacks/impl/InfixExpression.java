package com.catherine.stacks.impl;

import java.util.Stack;

/**
 * @author : Catherine
 */
public class InfixExpression {
    // ( 1 + ( 2 + 3 ) * ( 4 + 5 ) ) )

    /**
     * @param exp E.g., ( 1 + ( 2 + 3 ) * ( 4 + 5 ) ) )
     * @return 46
     */
    public int function(String exp) {
        int i = 0;
        String[] arr = exp.split(" ");
        Stack<Integer> vals = new Stack<>(); // operators
        Stack<String> ops = new Stack<>(); // operands
        String s;
        Integer t; // temp
        while (i < arr.length) {
            s = arr[i];
            switch (s) {
                case "(":
                    break;
                case ")":
                    String op = ops.pop();
                    switch (op) {
                        case "+" -> vals.push(vals.pop() + vals.pop());
                        case "-" -> {
                            t = vals.pop();
                            vals.push(t - vals.pop());
                        }
                        case "*" -> vals.push(vals.pop() * vals.pop());
                        case "/" -> {
                            t = vals.pop();
                            vals.push(t / vals.pop());
                        }
                    }
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    ops.push(arr[i]);
                    break;
                default:
                    vals.push(Integer.parseInt(s));
                    break;
            }
            i++;
        }
        return vals.pop();
    }
}
