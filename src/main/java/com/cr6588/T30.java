package com.cr6588;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class T30 {
    
    @Test
    public void findSubstringTest() throws Exception {
//        System.out.println(findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"}));
//        String s = "wordgoodgoodgoodbestword";
//        System.out.println(findSubstring(s, new String[]{"word","good","best","word"}));
        String s = "";
        String[] t = new String[5];
        for(int i = 0;  i < 5; i++) {
            s +="a";
            t[i] ="a";
        }
        System.out.println(findSubstring(s, t));
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if("".equals(s)) {
            return res;
        }
        if(words.length == 0) {
            return res;
        }
        List<List<Integer>> wordsStart = new ArrayList<>();
        Map<String, List<Integer>> wordsStartM = new HashMap<>();
        for(String word : words) {
            if("".equals(word)) {
                break;
            }
            List<Integer> list = wordsStartM.get(word);
            if(list == null) {
                list = getNum(s, word);
                wordsStartM.put(word, list);
            }
            if(list.size() == 0) {
                return res;
            }
            wordsStart.add(list);
        }

        List<List<Integer>> all = new ArrayList<>();
        for(Integer i : wordsStart.get(0)) {
            List<Integer> t = new ArrayList<>();
            t.add(i);
            all.add(t);
        }
        for (int i = 1; i < wordsStart.size(); i++) {
            List<Integer> cur = wordsStart.get(i);
            List<List<Integer>> allTemp = new ArrayList<>();
            Set<String> set = new HashSet<>();
            for (Integer j : cur) {
                for (List<Integer> per : all) {
                    List<Integer> newPer = new ArrayList<>();
                    boolean hasAddJ = false;
                    boolean hasEqual = false;
                    for (Integer perI : per) {
                        if(perI == j) {
                            hasEqual = true;
                            break;
                        }
                        if(j < perI && !hasAddJ) {
                            hasAddJ = true;
                            newPer.add(j);
                        }
                        newPer.add(perI);
                    }
                    if(newPer.size() == 0 || hasEqual) {
                        continue;
                    }
                    if(!hasAddJ) {
                        newPer.add(j);
                    }
                    String newPerStr = newPer.toString();
                    if(!set.contains(newPerStr)) {
                        set.add(newPerStr);
                        allTemp.add(newPer);
                    }
                }
            }
            all.clear();
            all.addAll(allTemp);
        }
        int length = words[0].length();
        for(List<Integer> r: all) {
            Integer start = r.get(0);
            Integer last = start;
            boolean hasError = false;
            for(int i = 1; i < r.size(); i++) {
                Integer cur = r.get(i);
                if(cur != last + length) {
                    hasError = true;
                    break;
                }
                last = cur;
            }
            if(!hasError && !res.contains(start)) {
                res.add(start);
            }
        }
        return res;
    }

    List<Integer> getNum(String s, String word) {
        List<Integer> res = new ArrayList<>();
        for(int k = 0;k < s.length() ;) {
            int i = 0;
            try {
                i = s.indexOf(word, k);
            } catch (Exception e) {
                System.out.println("i=" + i + "k=" + k);
                e.printStackTrace();
            }
            if(i == -1) {
                break;
            }
            res.add(i);
            k = i + 1;
        }
        return res;
    }

    @Test
    public void indexOfTest() throws Exception {
//        String s ="bbbbb  bbasdfbasdfabbas";
//        for(int k = 0; ;) {
//            int i = s.indexOf("", k);
//            if(i == -1) {
//                break;
//            }
//            k = i + 1;
//            System.out.println(i);
//        }
        List<Integer> t = new ArrayList<>();
//
        t.add(1);
        t.add(2);
        String s = t.toString();
        Set<String> set = new HashSet<>();
        set.add(s);
        System.out.println(set);
    }
}
