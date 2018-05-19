package com.company;

import java.util.ArrayList;

public class LazyBalancedBinarySearchTree<T> {
    private ArrayList<Node> nodeList = new ArrayList<>();

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
        public Boolean clean = false;

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
                    "id=" + id +
                    ", left=" + (left == null ? "null" : left.id) +
                    ", right=" + (right == null ? "null" : right.id) +
                    ", parent=" + (parent == null ? "null" : parent.id) +
                    ", leftMostOfRightSubTree=" + (leftMostOfRightSubTree == null ? "null" : leftMostOfRightSubTree.id) +
                    ", rightMostOfLeftSubTree=" + (rightMostOfLeftSubTree == null ? "null" : rightMostOfLeftSubTree.id) +
                    ", rightWeight=" + rightWeight +
                    ", leftWeight=" + leftWeight +
                    ", data=" + data +
                    "}";
        }
    }

    //Walk from root, or walk from current
    public Node root = null;
    public Node globalCurrent = null;

    public Node add(int id, T data) throws Exception {
        Node newNode = new Node(id, data);
        nodeList.add(newNode);
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
        return newNode;
    }

    private void weightDistribute(Node current) {
        if (current != null) {
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
                    newNode.parent.parent.leftWeight = 0;
                    newNode.parent.parent.rightWeight = 0;
                    newNode.parent.parent = null;
                    root = newNode.parent;
                } else {
                    newNode.parent.right = newNode.parent.parent;
                    if (newNode.parent.parent.parent.left == newNode.parent.parent) {
                        newNode.parent.parent.parent.left = newNode.parent;
                    } else {
                        newNode.parent.parent.parent.right = newNode.parent;
                    }
//                    newNode.parent = newNode.parent.parent.parent;
                    newNode.parent.parent.left = null;
                    newNode.parent.parent.leftWeight = 0;
                    newNode.parent.parent.rightWeight = 0;
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
                    newNode.parent.parent.leftWeight = 0;
                    newNode.parent.parent.rightWeight = 0;
                    newNode.parent.parent = null;
                    root = newNode.parent;
                } else {
                    newNode.parent.left = newNode.parent.parent;
                    if (newNode.parent.parent.parent.left == newNode.parent.parent) {
                        newNode.parent.parent.parent.left = newNode.parent;
                    } else {
                        newNode.parent.parent.parent.right = newNode.parent;
                    }
//                    newNode.parent = newNode.parent.parent.parent;
                    newNode.parent.parent.right = null;
                    newNode.parent.parent.leftWeight = 0;
                    newNode.parent.parent.rightWeight = 0;
                    Node parent = newNode.parent.parent.parent;
                    newNode.parent.parent.parent = newNode.parent;
                    newNode.parent.parent = parent;
                }
            } else if (newNode.parent.left == null && newNode.parent.parent.right == null) {
                if (newNode.parent.parent == root) {
                    //new root
                    newNode.right = newNode.parent.parent;
                    newNode.parent.parent.left = null;
                    newNode.left = newNode.parent;
                    newNode.parent.right = null;
                    newNode.right.parent = newNode;
                    newNode.left.parent = newNode;
                    newNode.right.leftWeight = 0;
                    newNode.right.rightWeight = 0;
                    newNode.left.leftWeight = 0;
                    newNode.left.rightWeight = 0;
                    newNode.parent = null;
                    root = newNode;
                } else {
                    if (newNode.parent.parent.parent.left == newNode.parent.parent) {
                        newNode.parent.parent.parent.left = newNode;
                    } else {
                        newNode.parent.parent.parent.right = newNode;
                    }
                    Node parent = newNode.parent.parent.parent;
                    newNode.right = newNode.parent.parent;
                    newNode.parent.parent.left = null;
                    newNode.left = newNode.parent;
                    newNode.parent.right = null;
                    newNode.right.parent = newNode;
                    newNode.left.parent = newNode;
                    newNode.right.leftWeight = 0;
                    newNode.right.rightWeight = 0;
                    newNode.left.leftWeight = 0;
                    newNode.left.rightWeight = 0;
                    newNode.parent = parent;
                }
            } else if (newNode.parent.right == null && newNode.parent.parent.left == null) {
                if (newNode.parent.parent == root) {
                    //new root
                    newNode.left = newNode.parent.parent;
                    newNode.parent.parent.right = null;
                    newNode.right = newNode.parent;
                    newNode.parent.left = null;
                    newNode.left.parent = newNode;
                    newNode.right.parent = newNode;
                    newNode.right.leftWeight = 0;
                    newNode.right.rightWeight = 0;
                    newNode.left.leftWeight = 0;
                    newNode.left.rightWeight = 0;
                    newNode.parent = null;
                    root = newNode;
                } else {
                    if (newNode.parent.parent.parent.left == newNode.parent.parent) {
                        newNode.parent.parent.parent.left = newNode;
                    } else {
                        newNode.parent.parent.parent.right = newNode;
                    }
                    Node parent = newNode.parent.parent.parent;
                    newNode.left = newNode.parent.parent;
                    newNode.parent.parent.right = null;
                    newNode.right = newNode.parent;
                    newNode.parent.left = null;
                    newNode.left.parent = newNode;
                    newNode.right.parent = newNode;
                    newNode.left.leftWeight = 0;
                    newNode.left.rightWeight = 0;
                    newNode.right.leftWeight = 0;
                    newNode.right.rightWeight = 0;
                    newNode.parent = parent;
                }
            }
        }
    }

    private void balance() {
        boolean rebalance = false;
        if (root.leftWeight - root.rightWeight < -1) {
            rebalance = true;
            Node leftMostOfRightSubTree = root.right;
            while (leftMostOfRightSubTree.left != null) {
                leftMostOfRightSubTree = leftMostOfRightSubTree.left;
            }
            if (leftMostOfRightSubTree.parent.right == leftMostOfRightSubTree) {
                leftMostOfRightSubTree.left = root;
                root.parent = leftMostOfRightSubTree;
                leftMostOfRightSubTree.parent.right = null;
                leftMostOfRightSubTree.parent.rightWeight = 0;
                leftMostOfRightSubTree.parent = null;
                root = leftMostOfRightSubTree;
            } else {
                if (leftMostOfRightSubTree.right != null) {
                    leftMostOfRightSubTree.parent.left = leftMostOfRightSubTree.right;
                    leftMostOfRightSubTree.right.parent = leftMostOfRightSubTree.parent;
                }
                leftMostOfRightSubTree.left = root;
                root.parent = leftMostOfRightSubTree;
                leftMostOfRightSubTree.right = root.right;
                leftMostOfRightSubTree.parent.left = null;
                leftMostOfRightSubTree.parent.leftWeight = 0;
                root.right.parent = leftMostOfRightSubTree;
                root.right = null;
                root = leftMostOfRightSubTree;
                root.parent = null;
            }
        } else if (root.leftWeight - root.rightWeight > 1) {
            rebalance = true;
            Node rightMostOfLeftSubTree = root.left;
            while (rightMostOfLeftSubTree.right != null) {
                rightMostOfLeftSubTree = rightMostOfLeftSubTree.right;
            }
            if (rightMostOfLeftSubTree.parent.left == rightMostOfLeftSubTree) {
                rightMostOfLeftSubTree.right = root;
                root.parent = rightMostOfLeftSubTree;
                rightMostOfLeftSubTree.parent.left = null;
                rightMostOfLeftSubTree.parent.leftWeight = 0;
                rightMostOfLeftSubTree.parent = null;
                root = rightMostOfLeftSubTree;
            } else {
                if (rightMostOfLeftSubTree.left != null) {
                    rightMostOfLeftSubTree.parent.right = rightMostOfLeftSubTree.left;
                    rightMostOfLeftSubTree.left.parent = rightMostOfLeftSubTree.parent;
                }
                rightMostOfLeftSubTree.right = root;
                root.parent = rightMostOfLeftSubTree;
                rightMostOfLeftSubTree.left = root.left;
                rightMostOfLeftSubTree.parent.right = null;
                rightMostOfLeftSubTree.parent.rightWeight = 0;
                root.left.parent = rightMostOfLeftSubTree;
                root.left = null;
                root = rightMostOfLeftSubTree;
                root.parent = null;
            }
        }
        if (rebalance && root.left != null && root.right != null) {
            Node rLeft = root.left;
            Node rRight = root.right;
            while (rLeft.right != null || rLeft.left != null) {
                if (rLeft.right != null) {
                    rLeft = rLeft.right;
                } else {
                    rLeft = rLeft.left;
                }
            }
            while (rRight.left != null || rRight.right != null) {
                if (rRight.left != null) {
                    rRight = rRight.left;
                } else {
                    rRight = rRight.right;
                }
            }
            weightDistribute(rLeft);
            weightDistribute(rRight);
        }
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

    public String sortedOrder() {
        String aggregate = "";
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }

        while (current != null) {
            if (current.left != null && !current.left.clean) {
                current = current.left;
            } else {
                if (!current.clean) {
                    aggregate = aggregate.concat(current.toString() + "\n");
                    current.clean = true;
                }
                if (current.right != null && !current.right.clean) {
                    current = current.right;
                } else if ((current.right == null || current.right.clean) && (current.left == null || current.left.clean)) {
                    current = current.parent;
                }
            }

        }
        for (Node node : nodeList) {
            node.clean = false;
        }

        return aggregate;
    }
}
