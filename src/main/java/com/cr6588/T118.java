package com.cr6588;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class T118 {
    
    @Test
    public void generateTest() throws Exception {
        System.out.println(generate(5));
    }
    
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < numRows; i++) {
            List<Integer> t = new ArrayList<>();
            t.add(1);
            for(int j = 1; j < i; j++) {
                List<Integer> last = res.get(i - 1);
                t.add(last.get(j - 1) + last.get(j));
            }
            if(i > 0) {
                t.add(1);
            }
            res.add(t);
        }
        return res;
    }
}
