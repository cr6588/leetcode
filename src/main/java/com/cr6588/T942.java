package com.cr6588;

import org.junit.Test;

public class T942 {

    @Test
    public void test() {
        diStringMatch("IDID");

    }
    public int[] diStringMatch(String S) {
        int firstI = 0;
        int firstD = S.length();
        int[] res = new int[S.length() + 1];
        for(int i = 0; i < S.length(); i++) {
            if('I' == S.charAt(i)) {
                res[i] = firstI++;
            } else {
                res[i] = firstD--;
            }
        }
        res[S.length()] = firstI;
        return res;
    }
}
