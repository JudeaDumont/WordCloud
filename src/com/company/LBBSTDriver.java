package com.company;

public class LBBSTDriver {
    public static void main(String[] args) throws Exception {
        LazyBalancedBinarySearchTree lazyBalancedBinarySearchTree = new LazyBalancedBinarySearchTree();
        lazyBalancedBinarySearchTree.add(12, new String(""));
        lazyBalancedBinarySearchTree.add(8, new String(""));
        lazyBalancedBinarySearchTree.add(4, new String(""));
        lazyBalancedBinarySearchTree.add(3, new String(""));
        lazyBalancedBinarySearchTree.add(2, new String(""));
        System.out.println(lazyBalancedBinarySearchTree.sortedOrder());
    }
}

