package com.cr6588;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

/**
 * create in 2019年04月16日
 * @category TODO
 * @author chenyi
 */
public class T278 {

    private int i;

    @Test
    public void firstBadVersionTest() throws Exception {
//        1063376695, 2126753390
//        -552418605, 2126753390
//        i = 1702766719;
        Assert.assertEquals(1702766719, firstBadVersion(2126753390));
        System.out.println(Integer.MAX_VALUE);
    }
    
    public int firstBadVersion(int n) {
        return getfirstBadVersion(1, n);
    }

    public int getfirstBadVersion(int start, int end) {
        if(isBadVersion(start)) {
            if(start == 1) {
                return 1;
            }
            //TODO
        }
        if(isBadVersion(end)) {
            if(end == start + 1) {
                return end;
            }
            //int center = (start + end) /2;超出Integer范围
            int center = start + (end - start) /2;
            if(isBadVersion(center)) {
                return getfirstBadVersion(start, center);
            } else {
                System.out.println(center + ", " + end);
                return getfirstBadVersion(center, end);
            }
        }
        //全正确版本
        return 0;
    }

    private boolean isBadVersion(int n) {
        if(n < i) {
            return false;
        }
        return true;
    }
}
