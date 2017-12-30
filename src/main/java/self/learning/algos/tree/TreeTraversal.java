package self.learning.algos.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Implementation of InOrder, PreOrder and PostOrder recursive traversal
 * Implementation of Inorder, PreOrder and PostOrder non-recursive traversal
 * Implementation of LevelOrder tree traversal.
 *
 * @author Harshad Shrishrimal
 */
public class TreeTraversal<Key extends Comparable<Key>> {

    /**
     * Indicates the node in the tree
     *
     * @param <Key> generic data
     */
    private static class Node<Key> {
        Key data;
        Node<Key> left;
        Node<Key> right;
    }

    /**
     * The root of the tree
     */
    private Node<Key> root;

    public TreeTraversal() {
        this.root = null;
    }

    /**
     * Insert the key in BST
     *
     * @param key key to be inserted
     */
    public void put(Key key) {
        this.root = put(key, this.root);
    }

    /**
     * Recursively traverse the tree and place the {@code key} in it's right place.
     *
     * @param key  data to be inserted in the tree
     * @param root indicates the root of the tree
     * @return reference to the root node
     */
    private Node<Key> put(Key key, Node<Key> root) {

        // create and initialize the root node
        if (root == null) {
            return createNode(key);
        }

        int cmp = key.compareTo(root.data);
        if (cmp < 0) {
            root.left = put(key, root.left);
        }
        if (cmp > 0) {
            root.right = put(key, root.right);
        }

        return root;
    }

    /**
     * Create the BST Node.
     *
     * @param key the data the node represents.
     * @return Node in the tree
     */
    private Node<Key> createNode(Key key) {
        Node<Key> n = new Node();
        n.data = key;
        n.left = null;
        n.right = null;
        return n;
    }

    /**
     * Recursive pre-order traversal of tree
     * 1. visit the root.
     * 2. Traverse the left sub-tree
     * 3. Traverse the right sub-tree
     *
     * @param consumer lambda to execute for every node in the tree
     */
    public void recursivePreOrder(Consumer<Key> consumer) {
        recursivePreOrder(this.root, consumer);
    }

    /**
     * Recursive pre-order traversal of tree
     * 1. visit the root.
     * 2. Traverse the left sub-tree
     * 3. Traverse the right sub-tree
     *
     * @param root     node in the tree
     * @param consumer lambda to execute for every node in the tree
     */
    private void recursivePreOrder(Node<Key> root, Consumer<Key> consumer) {
        // exit condition
        if (root == null) {
            return;
        }
        consumer.accept(root.data);
        recursivePreOrder(root.left, consumer);
        recursivePreOrder(root.right, consumer);
    }

    /**
     * Recursive in-order traversal of tree
     * 1. Traverse the left sub-tree
     * 2. Visit the root
     * 3. Traverse the right sub-tree
     *
     * @param consumer lambda to execute for every node in the tree
     */
    public void recursiveInOrder(Consumer<Key> consumer) {
        recursiveInOrder(this.root, consumer);
    }

    /**
     * Recursive in-order traversal of tree
     * 1. Traverse the left sub-tree
     * 2. Visit the root
     * 3. Traverse the right sub-tree
     *
     * @param root     node in the tree
     * @param consumer lambda to execute for every node in the tree
     */
    private void recursiveInOrder(Node<Key> root, Consumer<Key> consumer) {
        // exit condition
        if (root == null) {
            return;
        }
        recursiveInOrder(root.left, consumer);
        consumer.accept(root.data);
        recursiveInOrder(root.right, consumer);

    }

    /**
     * Recursive post-order traversal of tree
     * 1. Traverse the left sub-tree
     * 2. Traverse the right sub-tree
     * 3. Visit the root
     *
     * @param consumer lambda to execute for every node in the tree
     */
    public void recursivePostOrder(Consumer<Key> consumer) {
        recursivePostOrder(this.root, consumer);
    }

    /**
     * Recursive post-order traversal of tree
     * 1. Traverse the left sub-tree
     * 2. Traverse the right sub-tree
     * 3. Visit the root
     *
     * @param root     node in the tree
     * @param consumer lambda to execute for every node in the tree
     */
    private void recursivePostOrder(Node<Key> root, Consumer<Key> consumer) {
        // exit condition
        if (root == null) {
            return;
        }
        recursivePostOrder(root.left, consumer);
        recursivePostOrder(root.right, consumer);
        consumer.accept(root.data);
    }

    /**
     * pre-order tree traversal without using recursion
     *
     * @param consumer lambda to execute for every node in the tree
     */
    public void nonRecursivePreOrder(Consumer<Key> consumer) {

        // base case
        if (this.root == null) {
            return;
        }

        /*
            Create stack and push root on it.
            pop the item and visit it.
            push the right sub tree
            push the left sub tree
            right is pushed first so that left sub tree will be processed first.
        */
        Deque<Node<Key>> stack = new ArrayDeque<>();
        stack.addFirst(this.root);

        while (!stack.isEmpty()) {

            Node<Key> visitNode = stack.removeFirst();
            consumer.accept(visitNode.data);

            if (visitNode.right != null) {
                stack.addFirst(visitNode.right);
            }

            if (visitNode.left != null) {
                stack.addFirst(visitNode.left);
            }

        }

    }

    /**
     * in-order tree traversal without using recursion
     *
     * @param consumer lambda to execute for every node in the tree
     */
    public void nonRecursiveInOrder(Consumer<Key> consumer) {

        // base case
        if (this.root == null) {
            return;
        }

        /*
         * Keep pushing the nodes on the stack and advance to left child.
         * If left child does not exits,
         *    pop from stack,
         *    visit the node and
         *    then advance to the right child of the node.
         */
        Deque<Node<Key>> stack = new ArrayDeque<>();
        Node<Key> currentNode = this.root;

        while (!stack.isEmpty() || currentNode != null) {

            if (currentNode != null) {
                stack.addFirst(currentNode);
                currentNode = currentNode.left;
            } else {
                currentNode = stack.removeFirst(); //pop from stack
                consumer.accept(currentNode.data); //visit the node
                currentNode = currentNode.right; //advance to the right child of node
            }

        }

    }

    /**
     * post-order tree traversal without using recursion
     *
     * @param consumer lambda to execute for every node in the tree
     */
    public void nonRecursivePostOrder(Consumer<Key> consumer) {

        // base case
        if (this.root == null) {
            return;
        }

        Deque<Node<Key>> stack = new ArrayDeque<>();
        Node<Key> currentNode = this.root;
        Node<Key> lastNodeTraversed = null;
        while (!stack.isEmpty() || currentNode != null) {

            //Keep traversing the left sub-tree pushing nodes on stack
            if (currentNode != null) {
                stack.addFirst(currentNode);
                currentNode = currentNode.left;
            } else {

                Node<Key> stackTop = stack.peekFirst();
                if (stackTop.right != null && stackTop.right != lastNodeTraversed) {
                    //i.e. last traversal node is not right element, so right sub tree is not
                    //yet, traversed. so we need to start iterating over right tree
                    //(note left tree is by default traversed by above case)
                    currentNode = stackTop.right;
                } else {
                    lastNodeTraversed = stack.removeFirst();
                    consumer.accept(lastNodeTraversed.data);
                }
            }
        }

    }

    /**
     * level order traversal of a binary tree.
     *
     * @param consumer lambda to execute for every node in the tree
     */
    public void levelOrderTraversal(Consumer<Key> consumer) {

        //base cases
        if (root == null) {
            return;
        }

        // Node to indicate the end of a level.
        final Node<Key> DELIMITER_NODE = new Node<>();
        DELIMITER_NODE.data = null;
        DELIMITER_NODE.left = DELIMITER_NODE.right = null;

        /*
         * Push each node in a queue.
         * if end of level then push delimiter.
         * loop till queue is not empty
         */

        Deque<Node<Key>> queue = new ArrayDeque<>();

        // add root and delimiter to the queue
        // delimiter indicated the end of level
        queue.addLast(this.root);
        queue.addLast(DELIMITER_NODE);

        //keep track of the lastVisitedNode.
        //used to exist from the while lopp below.
        //we exit when the current node and the last vistited node is the same.
        //this happens when all nodes are processed and delimiters are pushed twice on the queue.
        Node<Key> lastVisitedNode = null;

        while (!queue.isEmpty()) {

            Node<Key> currentNode = queue.removeFirst();

            // exit condition as there will always be one entry in queue
            if (lastVisitedNode == currentNode) {
                break;
            }

            if (currentNode.data != DELIMITER_NODE.data) {

                //visit the node
                consumer.accept(currentNode.data);

                //push left child if it exits
                if (currentNode.left != null) {
                    queue.addLast(currentNode.left);
                }

                //push right child if it exits
                if (currentNode.right != null) {
                    queue.addLast(currentNode.right);
                }

                //update the last visited node
                lastVisitedNode = currentNode;
            } else {
                queue.addLast(DELIMITER_NODE);
                lastVisitedNode = DELIMITER_NODE;
            }
        }

    }
}
