package com.company;

import java.util.Stack;

/**
 * A class that evaluates the value of an expression.
 */
public class Calculator {

    /**
     * Expression containing numbers, operators (+,-,*,/), brackets, spaces, other characters.
     */
    private String str;

    /**
     * Constructor of the Calculator class
     * @param str Expression
     */
    Calculator(String str) {
        this.str = str;
    }

    /**
     * Method for removing spaces from an expression
     */
    private void delSpace()
    {
        StringBuilder newstr = new StringBuilder();
        for (int pos = 0; pos < str.length(); pos++)
            if (str.charAt(pos) != ' ')
                newstr.append(str.charAt(pos));
        str = newstr.toString();
    }

    /**
     * The method of checking the expression for correctness
     * @return True if the expression is true, otherwise false
     */
    private boolean check()
    {
        if (str.isEmpty()) return false;
        else {
            delSpace();
            int bracket = 0;

            for (int pos = 0; pos < str.length(); pos++) {

                if (bracket >= 0) {

                    switch (str.charAt(pos)) {

                        case '+': case '-': case '*': case '/': {
                            if(pos == 0 || pos == str.length() - 1) return false;
                        else
                            if (str.charAt(pos+1) == '+' || str.charAt(pos+1) == '-' || str.charAt(pos+1) == '*' || str.charAt(pos+1) == '/' || str.charAt(pos+1) == ')')
                            return false;
                            break;
                        }

                        case '(': {
                            bracket++;
                            if (str.charAt(pos+1) == '+' || str.charAt(pos+1) == '-' || str.charAt(pos+1) == '*' || str.charAt(pos+1) == '/' ||  str.charAt(pos+1) == ')')
                                return false;
                            else if (pos == str.length() - 1) return false;
                            break;
                        }

                        case ')': {
                            bracket--;
                            if (pos == 0) return false;
                            else if (str.charAt(pos-1) == '+' || str.charAt(pos-1) == '-' || str.charAt(pos-1) == '*' || str.charAt(pos-1) == '/' || str.charAt(pos-1) == '(' )
                                return false;
                            break;
                        }

                        default:
                            if (str.charAt(pos) >= '0' && str.charAt(pos) <= '9') {
                                if (pos != 0)
                                    if (str.charAt(pos-1) == ')' )
                                        return false;
                                if (pos != str.length()-1)
                                    if (str.charAt(pos+1) == '(' )
                                        return false;
                            }
                            else return false;
                    }
                }
                else return false;
            }
            return bracket == 0;
        }
    }

    /**
     * A method that determines the operator's priority.
     * @param ch Symbol (operator, bracket)
     * @return Symbol priority
     */
    private int typeChar(char ch) {
        if (ch == '*' || ch == '/')
            return 3;
        else if (ch == '+' || ch == '-')
            return 2;
        else if (ch == '(')
            return 1;
        else if (ch == ')')
            return -1;
        return 0;
    }

    /**
     * A method that overwrites an expression into a postfix form.
     * @return True if it was possible to write, otherwise false.
     */
    private boolean postfixNotation() {

        if (!check() || str.isEmpty())
            return false;

        else {
            Stack<Character> charstack = new Stack<Character>();
            StringBuilder newstr = new StringBuilder();

            for (int pos = 0; pos < str.length(); pos++) {
                int typeoper = typeChar(str.charAt(pos));

                if (typeoper == 0) newstr.append(str.charAt(pos));

                else if (typeoper == 1) charstack.push(str.charAt(pos));

                else if (typeoper > 1) {
                    newstr.append(' ');

                    while (!charstack.empty()) {
                        if (typeChar(charstack.peek()) >= typeoper)
                            newstr.append(charstack.pop());
                        else break;
                    }

                    charstack.push(str.charAt(pos));
                }

                else if (typeoper == -1) {
                    newstr.append(' ');

                    while (typeChar(charstack.peek()) != 1)
                        newstr.append(charstack.pop());

                    charstack.pop();
                }
            }

            while (!charstack.empty()) newstr.append(charstack.pop());
            str = newstr.toString();
            return true;
        }
    }

    /**
     * A method that reads the value of an expression written in postfix form and writes the result in the field str.
     * @return True if it was calculated, otherwise false.
     */
    public boolean count() {

        boolean t = postfixNotation();
        if (!t) return false;
        else {

            StringBuilder res = new StringBuilder();
            Stack<Double> st = new Stack<Double>();

            for (int pos = 0;pos < str.length(); pos++) {

                if (str.charAt(pos) == ' ') continue;

                if (typeChar(str.charAt(pos)) == 0){

                    while (str.charAt(pos) != ' ' && typeChar(str.charAt(pos)) == 0) {
                        res.append(str.charAt(pos++));
                        if (pos == str.length()) break;
                    }

                    st.push(Double.parseDouble(res.toString()));
                    res = new StringBuilder();
                }

                if (typeChar(str.charAt(pos)) > 1) {

                    double num1 = st.pop();
                    double num2 = st.pop();

                    if (str.charAt(pos) == '+')
                        st.push(num2 + num1);

                    if (str.charAt(pos) == '-')
                        st.push(num2 - num1);

                    if (str.charAt(pos) == '*')
                        st.push(num2 * num1);

                    if (str.charAt(pos) == '/')
                        st.push(num2 / num1);
                }
            }
            str = Double.toString(st.pop());
            return true;
        }
    }

    /**
     * The method that displays the value of the field str on the screen.
     */
    @Override
    public String toString() {
        return str + "\n";
    }
}
