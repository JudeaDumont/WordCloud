package com.company;

public class LazyBalancedBinarySearchTree<T> {
    private class Node implements Comparable {
        public Node left = null;
        public Node right = null;
        public Node parent = null;
        public Node leftMostOfRightSubTree = null;
        public Node rightMostOfLeftSubTree = null;
        public int id = -1;
        public int weight = 0;
        public T data = null;

        Node(int id, T data) {
            this.id = id;
            this.data = data;
        }

        @Override
        public int compareTo(Object o) {
            Integer beforeOrAfter = null;
            try {
                beforeOrAfter = ((Node) o).id - this.id;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return beforeOrAfter;
        }
    }

    //Walk from root, or walk from current
    public Node root = null;
    public Node current = null;

    public Node add(int id, T data) throws Exception {
        Node newNode = new Node(id, data);
        if (root == null) {//todo: refactor, most the time the root is not null
            this.root = newNode;
        } else {
            //a better way to do this is by walking the tree, adding the node, and then fixing the tree.
            if (current == null) {//lazy walk
                current = root;
            }

            if (current.compareTo(newNode) < 0) { //left child
                if (current.left == null) {
                    current.left = newNode;
                    newNode.parent = current;
                    current.weight -= 1;
                } else {
                    //left child
                    //left child is not null, current comes after new
                    if (current.left.compareTo(newNode) < 0) {
                        //left child
                        //left child is not null, current comes after new
                        //in-between parent and left child
                        if (current.right == null) {
                            //left child
                            //left child is not null, current comes after new
                            //in-between parent and left child
                            if (current.left.right == null) {
                                //straight line left, rotate

                            } else {
                                //walk left // push new through function call stack

                            }
                        } else {
                            //left child
                            //left child is not null, current comes after new
                            //in-between parent and left child
                            //left is not null, right is not null, replace left with new, left becomes left child of new
                        }
                    } else {
                        //left child
                        //left child is not null,
                        //left child of left child of current
                        if (current.right == null) {
                            //left child
                            //left child is not null,
                            //left child of left child of current
                            //right is null
                            if (current.left.right == null) {
                                //left child
                                //left child is not null,
                                //left child of left child of current
                                //right is null
                                //lefts right is null, rotate

                            } else {
                                //left child
                                //left child is not null,
                                //left child of left child of current
                                //right is null
                                //lefts right is not null

                            }
                        } else { //left is not null, right is not null, replace left with new, left becomes left child of new
                            if (current.left.right == null) {//straight line left, rotate

                            } else {

                            }
                        }
                    }
                }
            } else if (current.compareTo(newNode) > 0) { //right child
                if (current.right == null) {
                    current.right = newNode;
                    newNode.parent = current;
                    current.weight += 1;
                } else { //right child is not null

                }
            } else if (current.left != null && current.right != null) { //todo:refactor for most the time this is true
                if (current.left.compareTo(newNode) > 0 && current.right.compareTo(newNode) < 0) { //between
                    if (current.compareTo(newNode) < 0) { //between left and parent

                    } else if (current.compareTo(newNode) > 0) {

                    } else if (current.compareTo(newNode) == 0) {
                        throw new Exception("Duplicate");
                    }
                }
            }
        }
        if (newNode == null) {
            throw new Exception("Node was never created");
        }
        return newNode;
    }
}
