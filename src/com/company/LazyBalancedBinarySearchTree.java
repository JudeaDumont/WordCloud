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
            if (current == null) {//lazy walk
                current = root;
            }

            if (current.compareTo(newNode) < 0 && current.left == null) { //left child
                current.left = newNode;
                newNode.parent = current;
                current.weight -= 1;
            } else if (current.compareTo(newNode) > 0 && current.right == null) { //right child
                current.right = newNode;
                newNode.parent = current;
                current.weight += 1;
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
