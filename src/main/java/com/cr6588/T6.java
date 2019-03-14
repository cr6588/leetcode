package com.cr6588;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * create in 2019年03月13日
 * @see https://leetcode-cn.com/problems/zigzag-conversion/
 * @author chenyi
 */
public class T6 {
    public String convert(String s, int numRows) {
        if(s ==  null || numRows < 1) {
            return "";
        }
        if(numRows == 1) {
            return s;
        }
        int one = (numRows - 1) * 2;
        StringBuilder res = new StringBuilder();
        int length = s.length();
        for(int j = 0; j < length; j+=one) {
            res.append(s.charAt(j));
        }
        for(int i = 1; i < numRows - 1; i++) {
            int j = i;
            for(int another = one - i ;another < length; j+= one, another += one) {
                res.append(s.charAt(j));
                res.append(s.charAt(another));
            }
            if(length > j) {
                res.append(s.charAt(j));
            }
        }
        for(int j = numRows - 1; j < length ; j += one) {
            res.append(s.charAt(j));
        }
        return res.toString();
    }

//    "PAHNAPLSIIGYIR"
//     PAHNAPLSIIGYIR

    
    @Test
    public void convertTest() throws Exception {
//        "PAYPALISHIRING"
//        4
//          PINALSIGYAHRPI
//          PINALSIGYAHRPI
        System.out.println(convert2("PAYPALISHIRING", 4));
    }

    public String convert2(String s, int numRows) {
        if(s ==  null || numRows < 1) {
            return "";
        }
        if(numRows == 1) {
            return s;
        }
        int one = (numRows - 1) * 2;
        Map<Integer, StringBuilder> m = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            int k = i % one;
            if(k > numRows - 1) {
                k = one - k;
            }
            StringBuilder sb = m.get(k);
            if(sb == null) {
                sb = new StringBuilder();
                m.put(k, sb);
            }
            sb.append(s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < numRows; i++) {
            StringBuilder sb = m.get(i);
            if(sb == null) {
                break;
            }
            res.append(sb);
        }
        return res.toString();
    }
}
