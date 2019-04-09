package com.cr6588;

import java.util.ArrayList;
import java.util.List;

public class T429 {
    
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        List<Integer> t = new ArrayList<>();
        t.add(root.val);
        res.add(t);

        getPer(root.children, res);

        return res;
    }

    private void getPer(List<Node> nodes, List<List<Integer>> res) {
        if(nodes == null || nodes.isEmpty()) {
            return;
        }
        List<Node> childrens = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        for(Node node:nodes) {
            a.add(node.val);
            childrens.addAll(node.children);
        }
        res.add(a);
        getPer(childrens,res);
    }
}
