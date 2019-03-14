package com.cr6588;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * create in 2019年03月14日
 * @see https://leetcode-cn.com/problems/minimum-falling-path-sum/submissions/
 * @author chenyi
 * @category
 */
public class T931 {

    @Test
    public void test() {
//        [[1,2,3],[4,5,6],[7,8,9]] 12
//        System.out.println(minFallingPathSum(new int[][]{{17,82},{1,-44}}));
        //{{-51,-35,74},{-62,14,-53},{94,61,-10}}
        System.out.println(minFallingPathSum(new int[][] {{-51,-35,74},{-62,14,-53},{94,61,-10}}));
    }

    public int minFallingPathSum(int[][] A) {
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < A[0].length; i++) {
            int temp = A[0][i];
            List<Integer> list = new ArrayList<>();
            list.add(temp);
            List<Integer> columnlist = new ArrayList<>();
            columnlist.add(0);
            for (int j = 1; j < A.length; j++) {
                List<Integer> tList = new ArrayList<>();
                for (int a = 0; a < list.size(); a++) {
                    int tt = list.get(a);
                    int aColumn = columnlist.get(a);
                    int kmin = aColumn == 0 ? 0 : aColumn - 1;
                    int kmax = aColumn + 2 < A[j].length ? aColumn + 2 : A[j].length;
                    for (int k = kmin; k < kmax; k++) {
                        if (k == kmin) {
                            list.set(a, tt + A[j][k]);
                            columnlist.set(a, k);
                            continue;
                        }
                        tList.add(tt + A[j][k]);
                        columnlist.set(list.size() + tList.size(), k);
                    }
                }
                list.addAll(tList);
            }
            resList.addAll(list);
        }
        int res = resList.get(0);
        for (int i : resList) {
            if (res > i) {
                res = i;
            }
        }
        return res;
    }
}
