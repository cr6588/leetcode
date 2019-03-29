package com.cr6588;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class T17 {
    @Test
    public void letterCombinationsTest() throws Exception {
        System.out.println(letterCombinations("789"));
    }
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        List<List<Character>> all = new ArrayList<>();
        for(int i = 0; i < digits.length(); i++) {
            List<Character> t = getStr(digits.charAt(i));
            all.add(t);
        }
        for(int i = 0;i < all.size(); i++) {
            List<Character> t = all.get(i);
            if( i == 0) {
                for(int j = 0; j < t.size(); j++) {
                    res.add(t.get(j) + "");
                }
                continue;
            }
            List<String> current = new ArrayList<>();
            for(int k = 0; k < res.size(); k++) {
                for(int j = 0; j < t.size(); j++) {
                    current.add(res.get(k) + t.get(j));
                }
            }
            res.clear();
            res.addAll(current);
        }
        return res;
    }

    public List<Character> getStr(char c) {
        List<Character> t = new ArrayList<>();
        if(c <= '7') {
            int first = 'a' + (Integer.parseInt(c + "") - 2) * 3;
            t.add((char) first);
            t.add((char) (first + 1));
            t.add((char) (first + 2));
            if(c == '7') {
                t.add((char)(first + 3));
            }
        } else {
            int first = 'a' + (Integer.parseInt(c + "") - 2) * 3 + 1;
            t.add((char) first);
            t.add((char) (first + 1));
            t.add((char) (first + 2));
            if(c == '9') {
                t.add((char)(first + 3));
            }
        }
        return t;
    }
}
