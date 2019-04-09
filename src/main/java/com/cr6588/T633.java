package com.cr6588;

import static org.junit.Assert.*;

import org.junit.Test;

public class T633 {
    
    public boolean judgeSquareSum(int c) {
        for(int a = 0; a*a <= c; a++) {
            int b2 = c - a*a;
            if(is(b2)) {
                return true;
            }
        }
        return false;
    }

    private boolean is(int b2) {
        for(int i = 0; i * i <= b2; i++) {
            if(i*i == b2) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testName() throws Exception {
        judgeSquareSum2(5);
    }

    public boolean judgeSquareSum2(int c) {
        for(int a = 0, b = (int)Math.sqrt(c);a <= b;) {
            int sum = a * a + b * b;
            if(sum == c) {
                return true;
            }
            if(sum > c) {
                b--;
            } else {
                a++;
            }
        }
        return false;
    }
}
