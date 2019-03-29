package com.cr6588;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class T30 {
    
    @Test
    public void findSubstringTest() throws Exception {
        System.out.println(findSubstring3("barfoofoobarthefoobarman", new String[]{"bar","foo"}));
//        String s = "wordgoodgoodgoodbestword";
//        System.out.println(findSubstring(s, new String[]{"word","good","best","word"}));
        String s = "";
        String[] t = new String[50];
        for(int i = 0;  i < 50; i++) {
            s +="a";
            t[i] ="a";
        }
//        System.out.println(findSubstring2(s, t));
//        System.out.println(findSubstring2("aaaaaa", new String[]{"aaa","aaa"}));
//        System.out.println(findSubstring2("aaa", new String[]{"aa","aa"}));
        s = "";
        t = new String[10000];
        for(int i = 0;  i < 10000; i++) {
            s +="ab";
            if( i% 2 == 0) {
                t[i] ="ab";
            } else {
                t[i] ="ba";
            }
        }
//        System.out.println(findSubstring3(s, t));
    }

    //穷举
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

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if("".equals(s)) {
            return res;
        }
        if(words.length == 0) {
            return res;
        }
        List<List<Integer>> wordsStart = new ArrayList<>();
        Map<String, List<Integer>> wordsStartM = new HashMap<>();
        Map<String, Integer> wordsCount = new HashMap<>();
        for(String word : words) {
            if("".equals(word)) {
                break;
            }
            List<Integer> list = wordsStartM.get(word);
            if(list == null) {
                list = getNum(s, word);
                wordsStartM.put(word, list);
                wordsCount.put(word, 1);
            } else {
                wordsCount.put(word, wordsCount.get(word) + 1);
            }
            if(list.size() == 0 || wordsCount.get(word) > list.size()) {
                return res;
            }
            wordsStart.add(list);
        }

        List<List<Integer>> all = new ArrayList<>();
        int length = words[0].length();
        for(Entry<String, List<Integer>> entry : wordsStartM.entrySet()) {
            int count = wordsCount.get(entry.getKey());
            List<Integer> wordsStartTemp = entry.getValue();
            List<List<Integer>> cur = new ArrayList<>();
            for(int i = 0; i <= wordsStartTemp.size() - count; i++) {
                List<Integer> curChild = new ArrayList<>();
                Integer last = null;
                for(int j = i, k = 0; j < wordsStartTemp.size() && k < count; j++) {
                    Integer val = wordsStartTemp.get(j);
                    if( j == i) {
                        curChild.add(val);
                        last = val;
                        k++;
                        continue;
                    }
                    if((val - last) % length == 0) {
                        curChild.add(val);
                        last = val;
                        k++;
                    }
                }
                if(curChild.size() == count) {
                    cur.add(curChild);
                }
            }

            if(all.isEmpty()) {
                all.addAll(cur);
                continue;
            }

            List<List<Integer>> mergeAll = new ArrayList<>();
            for(List<Integer> source: all) {
                for(List<Integer> target: cur) {
                    List<Integer> merge = merge(source, target, length);
                    if(merge == null) {
                        continue;
                    }
                    mergeAll.add(merge);
                }
            }
            all.clear();
            all.addAll(mergeAll);
        }

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

    private List<Integer> merge(List<Integer> source, List<Integer> target, int length) {
        List<Integer> t = new ArrayList<>();
        int sL = 0, tL = 0;
        for(; sL < source.size() && tL < target.size();) {
            int sNum = source.get(sL);
            int tNum = target.get(tL);
            if(sNum < tNum) {
                t.add(sNum);
                sL++;
            } else {
                t.add(tNum);
                tL++;
            }
            if(t.size() > 1 && (t.get(t.size() - 1) - t.get(t.size() - 2) )% length != 0) {
                return null;
            }
        }
        if(sL != source.size()) {
            for(int i = sL; i < source.size(); i++) {
                t.add(source.get(i));
            }
        } else {
            for(int i = tL; i < target.size(); i++) {
                t.add(target.get(i));
            }
        }
        if(t.size() > 1 && (t.get(t.size() - 1) - t.get(t.size() - 2) )% length != 0) {
            return null;
        }
        return t;
    }

    public List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if("".equals(s)) {
            return res;
        }
        if(words.length == 0) {
            return res;
        }
        Map<String, List<Integer>> wordsStartM = new HashMap<>();
        Map<String, Integer> wordsCount = new HashMap<>();
        Map<Integer, String> numWord = new HashMap<>();
        for(String word : words) {
            if("".equals(word)) {
                break;
            }
            List<Integer> list = wordsStartM.get(word);
            if(list == null) {
                list = getNum(s, word);
                wordsStartM.put(word, list);
                wordsCount.put(word, 1);
                for(Integer a: list) {
                    numWord.put(a, word);
                }
            } else {
                wordsCount.put(word, wordsCount.get(word) + 1);
            }
            if(list.size() == 0 || wordsCount.get(word) > list.size()) {
                return res;
            }
        }

        int length = words[0].length();
        for(Entry<Integer, String> entry: numWord.entrySet()) {
            int start = entry.getKey();
            Map<String, Integer> wordsCountCopy = new HashMap<>();
            wordsCountCopy.putAll(wordsCount);
            Boolean hasError = false;
            for(int i = start, k = 0; k < words.length; i += length, k++) {
                String word = numWord.get(i);
                Integer count = wordsCountCopy.get(word);
                if(count == null || count == 0) {
                    hasError = true;
                    break;
                }
                count--;
                wordsCountCopy.put(word, count);
            }
            if(hasError) {
                continue;
            }
            res.add(start);
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

    @Test
    public void mergeTest() throws Exception {
        List<Integer> source = new ArrayList<>();
        source.add(1);
        source.add(10);
        source.add(30);
        List<Integer> target = new ArrayList<>();
        target.add(0);
        target.add(7);
        target.add(60);
        System.out.println(merge(source, target, 3).toString());
    }

}
