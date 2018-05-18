package com.company;

public class LBBSTDriver {
    public static void main(String[] args) throws Exception {
        LazyBalancedBinarySearchTree lazyBalancedBinarySearchTree = new LazyBalancedBinarySearchTree();
        lazyBalancedBinarySearchTree.add(8, new String(""));
        lazyBalancedBinarySearchTree.add(16, new String(""));
        lazyBalancedBinarySearchTree.add(10, new String(""));
        lazyBalancedBinarySearchTree.add(13, new String(""));
        lazyBalancedBinarySearchTree.add(15, new String(""));
        lazyBalancedBinarySearchTree.add(14, new String(""));
        lazyBalancedBinarySearchTree.add(7, new String(""));
        lazyBalancedBinarySearchTree.add(6, new String(""));
        lazyBalancedBinarySearchTree.add(5, new String(""));
        lazyBalancedBinarySearchTree.add(4, new String(""));
        lazyBalancedBinarySearchTree.add(3, new String(""));
        lazyBalancedBinarySearchTree.add(2, new String(""));
        lazyBalancedBinarySearchTree.add(1, new String(""));

        System.out.println(lazyBalancedBinarySearchTree.sortedOrder());
    }
}

