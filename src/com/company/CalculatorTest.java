package com.company;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests of the Calculator class
 */
public class CalculatorTest {

    @Test
    public void calculate_test1() {
        String str = "445+989*(768-3)";
        Calculator exp = new Calculator(str);
        System.out.print(exp.calculate());
    }

    @Test
    public void calculate_test2() {
        String str = "445+989*(768-3))";
        Calculator exp = new Calculator(str);
        System.out.print(exp.calculate());
    }

    @Test
    public void calculate_test3() {
        String str = "445+989**(768-3)";
        Calculator exp = new Calculator(str);
        System.out.print(exp.calculate());
    }

    @Test
    public void calculate_test4() {
        String str = "445+989)*(768-3";
        Calculator exp = new Calculator(str);
        System.out.print(exp.calculate());
    }

    @Test
    public void calculate_test5() {
        String str = "(445+ 989)/(768-3)";
        Calculator exp = new Calculator(str);
        System.out.print(exp.calculate());
    }

    @Test
    public void calculate_test6() {
        String str = "445*r+989*(768-3)";
        Calculator exp = new Calculator(str);
        System.out.print(exp.calculate());
    }
}