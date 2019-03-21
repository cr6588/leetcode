package com.cr6588;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

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
        System.out.println(minFallingPathSum2(new int[][] {{1,2,3},{4,5,6},{7,8,9}}));
//        System.out.println(minFallingPathSum2(new int[][]{{-19,-1,-96,48,-94,36,16,55,-42,37,-59,6,-32,96,95,-58,13,-34,94,85},{17,44,36,-29,84,80,-34,50,-99,64,13,91,-27,25,-36,57,20,98,-100,-72},{-92,-75,86,90,-4,90,64,56,50,-63,10,-15,90,-66,-66,32,-69,-78,1,60},{21,51,-47,-43,-14,99,44,90,8,11,99,-62,57,59,69,50,-69,32,85,13},{-28,90,12,-18,23,61,-55,-97,6,89,36,26,26,-1,46,-50,79,-45,89,86},{-85,-10,49,-10,2,62,41,92,-67,85,86,27,89,-50,77,55,22,-82,-94,-98},{-50,53,-23,55,25,-22,76,-93,-7,66,-75,42,-35,-96,-5,4,-92,13,-31,-100},{-62,-78,8,-92,86,69,90,-37,81,97,53,-45,34,19,-19,-39,-88,-75,-74,-4},{29,53,-91,65,-92,11,49,26,90,-31,17,-84,12,63,-60,-48,40,-49,-48,88},{100,-69,80,11,-93,17,28,-94,52,64,-86,30,-9,-53,-8,-68,-33,31,-5,11},{9,64,-31,63,-84,-15,-30,-10,67,2,98,73,-77,-37,-96,47,-97,78,-62,-17},{-88,-38,-22,-90,54,42,-29,67,-85,-90,-29,81,52,35,13,61,-18,-94,61,-62},{-23,-29,-76,-30,-65,23,31,-98,-9,11,75,-1,-84,-90,73,58,72,-48,30,-81},{66,-33,91,-6,-94,82,25,-43,-93,-25,-69,10,-71,-65,85,28,-52,76,25,90},{-3,78,36,-92,-52,-44,-66,-53,-55,76,-7,76,-73,13,-98,86,-99,-22,61,100},{-97,65,2,-93,56,-78,22,56,35,-24,-95,-13,83,-34,-51,-73,2,7,-86,-19},{32,94,-14,-13,-6,-55,-21,29,-21,16,67,100,77,-26,-96,22,-5,-53,-92,-36},{60,93,-79,76,-91,43,-95,-16,74,-21,85,43,21,-68,-32,-18,18,100,-43,1},{87,-31,26,53,26,51,-61,92,-65,17,-41,27,-42,-14,37,-46,46,-31,-74,23},{-67,-14,-20,-85,42,36,56,9,11,-66,-59,-55,5,64,-29,77,47,44,-33,-77}}));
        //{{-51,-35,74},{-62,14,-53},{94,61,-10}}
//        System.out.println(minFallingPathSum(new int[][] {{-51,-35,74},{-62,14,-53},{94,61,-10}}));
//        System.out.println(minFallingPathSum2(new int[][] {{1,1,1},{5,6,4},{0,2,2}}));
    }

    //第i行j列最小值是第i - 1行j列与相邻列的最小值,求和时用res的值不要用A的值
    public int minFallingPathSum2(int[][] A) {
        int rows = A.length;
        int res[][] = new int[rows][A[0].length];
        for(int i = 0; i < A[0].length; i++) {
            res[0][i] = A[0][i];
        }
        for(int i = 1; i < rows; i++) {
            for(int j = 0; j < A[i].length; j++) {
                int kmin = j == 0 ? j : j - 1;
                int kmax = j == A[i].length - 1? j : j + 1;
                int kValMin = res[i - 1][kmin];
                for(int k = kmin + 1; k <= kmax; k++) {
                    if(kValMin > res[i - 1][k]) {
                        kValMin = res[i - 1][k];
                    }
                }
                res[i][j] = kValMin + A[i][j];
            }
        }
        int min = res[rows - 1][0];
        for(int i = 1; i < res[rows - 1].length; i++) {
            if(min > res[rows - 1][i]) {
                min = res[rows - 1][i];
            }
        }
        return min;
    }

    //穷举太多且没必要
    public int minFallingPathSum(int[][] A) {
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < A[0].length; i++) {
            int temp = A[0][i];
            List<Integer> list = new ArrayList<>();
            list.add(temp);
            Map<Integer, Integer> m = new HashMap<>();
            m.put(0, i);
            for (int j = 1; j < A.length; j++) {
                List<Integer> tList = new ArrayList<>();
                for (int a = 0; a < list.size(); a++) {
                    int tt = list.get(a);
                    int aColumn = m.get(a);
                    int kmin = aColumn == 0 ? 0 : aColumn - 1;
                    int kmax = aColumn + 2 < A[j].length ? aColumn + 2 : A[j].length;
                    for (int k = kmin; k < kmax; k++) {
                        if (k == kmin) {
                            list.set(a, tt + A[j][k]);
                            m.put(a, k);
                            continue;
                        }
                        tList.add(tt + A[j][k]);
                        m.put(list.size() + tList.size() - 1, k);
                    }
                }
                list.addAll(tList);
                System.out.println("j==" + j + ",size=" + list.size());
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
