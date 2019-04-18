package com.cr6588;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import org.junit.Assert;
import org.junit.Test;

public class T678 {

    @Test
    public void checkValidStringTest() throws Exception {
        Assert.assertTrue(checkValidString("()"));
        Assert.assertTrue(checkValidString("(*)"));
        Assert.assertTrue(checkValidString("(*))"));
        Assert.assertTrue(checkValidString("(*()"));

    }

    public boolean checkValidString(String s) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> xin = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left.add(i);
            } else if (ch == ')') {
                if (left.size() > 0) {
                    left.remove(left.size() - 1);
                } else if (xin.size() > 0) {
                    xin.remove(xin.size() - 1);
                } else {
                    right.add(i);
                }
            } else if (ch == '*') {
                xin.add(i);
            }
        }
        if (right.size() > 0) {
            return false;
        }
        for (int i = left.size() - 1; i >= 0; i--) {
            if (xin.size() == 0) {
                return false;
            }
            if (xin.get(xin.size() - 1) < left.get(i)) {
                return false;
            }
            xin.remove(xin.size() - 1);
        }
        return true;
    }
}
