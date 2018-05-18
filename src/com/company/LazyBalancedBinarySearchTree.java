package com.company;

public class LazyBalancedBinarySearchTree<T> {
    private class Node implements Comparable {
        public Node left = null;
        public Node right = null;
        public Node parent = null;
        public Node leftMostOfRightSubTree = null;
        public Node rightMostOfLeftSubTree = null;
        public int id = -1;
        public int rightWeight = 0;
        public int leftWeight = 0;
        public T data = null;

        Node(int id, T data) {
            this.id = id;
            this.data = data;
        }

        @Override
        public int compareTo(Object o) {
            Integer beforeOrAfter = null;
            try {
                beforeOrAfter = this.id - ((Node) o).id;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return beforeOrAfter;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + left.id +
                    ", right=" + right.id +
                    ", parent=" + parent.id +
                    ", leftMostOfRightSubTree=" + leftMostOfRightSubTree.id +
                    ", rightMostOfLeftSubTree=" + rightMostOfLeftSubTree.id +
                    ", id=" + id +
                    ", rightWeight=" + rightWeight +
                    ", leftWeight=" + leftWeight +
                    ", data=" + data +
                    '}';
        }
    }

    //Walk from root, or walk from current
    public Node root = null;
    public Node globalCurrent = null;

    public Node add(int id, T data) throws Exception {
        Node newNode = new Node(id, data);
        Node localCurrent = root;

        if (root == null) {//todo: refactor, most the time the root is not null
            this.root = newNode;
        } else {
            //a better way to do this is by walking the tree, adding the node, and then fixing the tree.
//            if (current == null) {//lazy walk
//                current = root;
//            }
            walk(newNode, localCurrent);
            localBalance(newNode);
            weightDistribute(newNode); //called appropriately from localbalance case
            balance();

        }
        if (newNode == null) {
            throw new Exception("Node was never created");
        }
        return newNode;
    }

    private void weightDistribute(Node current) {
        while (current != null) {
            if (current.left != null) {
                current.leftWeight = current.left.rightWeight + current.left.leftWeight + 1;
            }
            if (current.right != null) {
                current.rightWeight = current.right.rightWeight + current.right.leftWeight + 1;
            }
            weightDistribute(current.parent);
        }
    }

    private void localBalance(Node newNode) {
        //Already determined that the newNode has a parent otherwise this function would not have been called,
        // only node that does not have a parent is the root.

        //top level parent of rotation might point to the rotated section via left or right child pointer,
        // compensate for this.
        // the weight of nodes shifts with a rotation, compensate for this
        if (newNode.parent.parent != null) {
            if (newNode.parent.right == null && newNode.parent.parent.right == null) {
                if (newNode.parent.parent == root) {
                    //new root
                    newNode.parent.right = newNode.parent.parent;
                    newNode.parent.parent.parent = newNode.parent;
                    newNode.parent.parent.left = null;
                    newNode.parent.parent = null;
                    root = newNode.parent;
                } else {
                    newNode.parent.right = newNode.parent.parent;
                    newNode.parent.parent.parent.left = newNode.parent;
                    newNode.parent.parent.left = null;
                    Node parent = newNode.parent.parent.parent;
                    newNode.parent.parent.parent = newNode.parent;
                    newNode.parent.parent = parent;
                }
            } else if (newNode.parent.left == null && newNode.parent.parent.left == null) {
                if (newNode.parent.parent == root) {
                    //new root
                    newNode.parent.left = newNode.parent.parent;
                    newNode.parent.parent.parent = newNode.parent;
                    newNode.parent.parent.right = null;
                    newNode.parent.parent = null;
                    root = newNode.parent;
                } else {
                    newNode.parent.left = newNode.parent.parent;
                    newNode.parent.parent.parent.right = newNode.parent;
                    newNode.parent.parent.right = null;
                    Node parent = newNode.parent.parent.parent;
                    newNode.parent.parent.parent = newNode.parent;
                    newNode.parent.parent = parent;
                }
            }else if (newNode.parent.left == null && newNode.parent.parent.right == null) {
                if (newNode.parent.parent == root) {
                    //new root
                    newNode.right = newNode.parent.parent;
                    newNode.left = newNode.parent;
                    newNode.right.parent = newNode;
                    newNode.left.parent = newNode;
                    newNode.right.leftWeight = 0;
                    newNode.right.rightWeight = 0;
                    newNode.left.leftWeight = 0;
                    newNode.left.rightWeight = 0;
                    root = newNode;
                } else {
                    newNode.parent.left = newNode.parent.parent;
                    newNode.parent.parent.parent.right = newNode.parent;
                    newNode.parent.parent.right = null;
                    Node parent = newNode.parent.parent.parent;
                    newNode.parent.parent.parent = newNode.parent;
                    newNode.parent.parent = parent;
                }
            }
        }
    }

    private void balance() {
        Node localCurrent = root;
    }

    private void walk(Node newNode, Node current) throws Exception {
        if (current.compareTo(newNode) > 0) {
            if (current.left == null) {
                current.left = newNode;
                newNode.parent = current;
            } else {
                walk(newNode, current.left);
            }
        } else if (current.compareTo(newNode) < 0) {
            if (current.right == null) {
                current.right = newNode;
                newNode.parent = current;
            } else {
                walk(newNode, current.right);
            }
        } else if (current.compareTo(newNode) == 0) {
            throw new Exception("Duplicate Node ID" + newNode.toString());
        }
    }
}
