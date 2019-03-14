package com.cr6588;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * create in 2019年03月13日
 * @see https://leetcode-cn.com/problems/odd-even-jump/
 * @author chenyi
 */
public class T975 {
    
    @Test
    public void main() {
//        [10,13,12,14,15]
//            输出：2
        //[235,82273,52834,9778,11890,64926,87365,27998,97851,10018,85393,39669,8134,27471,45297,79234,86135,13342,52086,65078,34705,87186,27840,1857,4066,68099,55835,94650,93843,90985,4200,74913,70132,65536,71235,90271,64014,37244,53792,20784,58712,889,15102,33340,78962,71849,33825,62053,4120,16386]
        //24
        
        
        //2,3,4,7,9,11,12,13,14,17,18,20,22,26,30,37,38,39,40,42,43,46,47,48
        //2,3,4,7,9,11,12,13,14,17,18,20,22,26,30,37,38,39,40,42,43,46,48
            System.out.println(oddEvenJumps2(new int[]{235,82273,52834,9778,11890,64926,87365,27998,97851,10018,85393,39669,8134,27471,45297,79234,86135,13342,52086,65078,34705,87186,27840,1857,4066,68099,55835,94650,93843,90985,4200,74913,70132,65536,71235,90271,64014,37244,53792,20784,58712,889,15102,33340,78962,71849,33825,62053,4120,16386}));
    }
    //1.数组
    public int oddEvenJumps(int[] A) {
        if(A.length == 0) {
            return 0;
        }
        if(A.length == 1) {
            return 1;
        }
        int res = 1;
        boolean oddJump = true;
        for(int i = 0; i < A.length - 1; i++) {
            if(jump(A, i, oddJump)) {
                System.out.print(i + ",");
                res++;
            }
        }
        return res;
    }

    private boolean jump(int[] A, int i, boolean oddJump) {
        if(i == A.length - 1) {
            return true;
        }
        Integer temp = null;
        Integer minj = null;
        if(oddJump) {
            for(int j = i + 1; j < A.length; j++) {
                if(A[i] <= A[j] && (temp == null || A[j] < temp)) {
                    temp = A[j];
                    minj = j;
                }
            }
        } else {
            for(int j = i + 1; j < A.length; j++) {
                if(A[i] >= A[j] && (temp == null || A[j] > temp)) {
                    temp = A[j];
                    minj = j;
                }
            }
        }
        if(temp == null) {
            return false;
        }
        return jump(A, minj, oddJump ? false : true);
    }

    //2.缓存部分结果
    public int oddEvenJumps2(int[] A) {
        if(A.length == 0) {
            return 0;
        }
        if(A.length == 1) {
            return 1;
        }
        int res = 1;
        Map<String, String> m = new HashMap<>();
        Set<Integer> succ = new HashSet<>();
        for(int i = 0; i < A.length - 1; i++) {
            if(succ.contains(i)) {
                res++;
                continue;
            }
            Set<Integer> temp = new HashSet<>();
            if(jumpsave(A, i, true, m, temp)) {
                res++;
            }
        }
        return res;
    }

    private boolean jumpsave(int[] A, int i, boolean oddJump, Map<String, String> m, Set<Integer> tempSetOddJump) {
        if(i == A.length - 1) {
            return true;
        }
        Integer temp = null;
        Integer minj = null;
        if(oddJump) {
            String nextMinj = m.get(i + "_" + oddJump);
            if(nextMinj != null) {
                minj = Integer.parseInt(nextMinj.split("_")[0]);
            } else {
                for(int j = i + 1; j < A.length; j++) {
                    if(A[i] <= A[j] && (temp == null || A[j] < temp)) {
                        temp = A[j];
                        minj = j;
                    }
                }
            }
            if(minj != null) {
                tempSetOddJump.add(minj);
            }
        } else {
            String nextMinj = m.get(i + "_" + false);
            if(nextMinj != null) {
                minj = Integer.parseInt(nextMinj.split("_")[0]);
            } else {
                for(int j = i + 1; j < A.length; j++) {
                    if(A[i] >= A[j] && (temp == null || A[j] > temp)) {
                        temp = A[j];
                        minj = j;
                    }
                }
            }
        }
        if(minj == null) {
            return false;
        }
        boolean next = oddJump ? false : true;
        m.put(i + "_" + oddJump, minj + "_" + next);

        return jumpsave(A, minj, next, m, tempSetOddJump);
    }
}
