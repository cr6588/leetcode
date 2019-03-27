package com.cr6588;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class T119 {
    
    @Test
    public void generateTest() throws Exception {
        System.out.println(getRow1(4));
        System.out.println(Ok);
    }

public static int Ok = 0;

public List<Integer> getRow1(int rowIndex) {
    List<Integer> ans = new ArrayList<>();
    ans.add(1);
    for (int i = 1; i <= rowIndex; i++) {
        Ok++;
        ans.add(1);
        for (int j = i - 1; j >= 1; j--) {
            Ok++;
            ans.set(j, ans.get(j) + ans.get(j - 1));
        }
    }
    return ans;
}




    public List<Integer> getRow(int rowIndex) {
        
        List<Integer> res = new ArrayList<>();
        res.add(1);
        if(rowIndex == 0) {
            Ok++;
            return res;
        }
        for(int i = 1; i <= rowIndex/2; i++) {
            if(i == 1) {
                Ok++;
                res.add(rowIndex);
                continue;
            }
            res.add(get(i,rowIndex));
        }
//        for(int i = (rowIndex + 1)/2 - 1; i >= 0; i--) {
//            Ok++;
//            res.add(res.get(i));
//        }
        return res;
    }

    private int get(int i, int rowIndex) {
        Ok++;
        if(i == 0 || i == 1 || rowIndex == 1 || rowIndex == 0) {
            return 1;
        }
        return get(i, rowIndex - 1) + get(i - 1, rowIndex - 1);
    }
}
