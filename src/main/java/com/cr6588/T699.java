package com.cr6588;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class T699 {

    //{{1, 2}, {2, 3}, {6, 1}}
    @Test
    public void testName() throws Exception {
//        System.out.println(fallingSquares(new int[][]{{100, 100}, {200, 100}}));//[100, 100]
//        System.out.println(fallingSquares(new int[][]{{1, 2}, {2, 3}, {6, 1}}));//[2, 5, 5]
        System.out.println(fallingSquares(new int[][]{{1, 5}, {2, 2}, {7, 5}}));   //[5, 7, 7]
        //{{2,82},{57,22},{16,66},{46,15},{5,11},{9,83},{1,32},{87,91},{64,61},{87,53}}
        System.out.println(fallingSquares(new int[][]{{2,82},{57,22},{16,66},{46,15},{5,11},{9,83},{1,32},{87,91},{64,61},{87,53}}));   //[82,104,170,185,185,268,300,359,420,473]
    }
    
    public List<Integer> fallingSquares(int[][] positions) {
        int maxHight = positions[0][1], maxX1 = positions[0][0], maxX2 = maxX1 + positions[0][1];
        int lastHight = 0;
        List<Integer> res = new ArrayList<>();
        res.add(maxHight);
        for(int i = 1; i < positions.length; i++) {
            int cX1 = positions[i][0], cX2 = cX1 + positions[i][1];
            if(cX1 < maxX1 && cX2 > maxX1 || cX1 < maxX2 && cX2 > maxX2 || cX1 >= maxX1 && cX2 <= maxX2) {
                lastHight = maxHight;
                maxHight += positions[i][1];
                maxX1 = cX1;
                maxX2 = cX2;
            } else if(lastHight + positions[i][1] < maxHight) {
                
            } else {
                int curHeight = getCurHeight(i - 1, positions, cX1, cX2, positions[i][1]);
                if(curHeight > maxHight) {
                    lastHight = maxHight;
                    maxHight = curHeight;
                    maxX1 = positions[i][0];
                    maxX2 = maxX1 + positions[i][1];
                }
                
            }
            res.add(maxHight);
        }
        return res;
    }

    private int getCurHeight(int k, int[][] positions, int cX1, int cX2, int curHeight) {
        for(;k >= 0;k--) {
            int kX1 = positions[k][0], kX2 = kX1 + positions[k][1];
            if(kX1 < cX1 && kX2 > cX1 || kX1 < cX2 && kX2 > cX2  || kX1 >= cX1 && kX2 <= cX2) {
                curHeight += positions[k][1];
                cX1 = kX1;
                cX2 = kX2;
            }
        }
        return curHeight;
    }
}
