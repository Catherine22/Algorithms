package com.catherine.trees;

/**
 * Left-leaning rad-black BST
 *
 * @author : Catherine
 */
public class LLRBTree<T extends Comparable<? super T>, Object> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        T key;
        Object val;
        Node left, right;
        /**
         * color of parent link
         */
        boolean color;

        Node() {
            this(null, null);
        }

        Node(T key, Object val) {
            this(null, null, RED);
        }

        Node(T key, Object val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    /**
     * The search function is the same as for elementary BST (ignore color)
     *
     * @param key
     * @return
     */
    public Object get(T key) {
        Node n = root;
        int cmp;
        while (n != null) {
            cmp = n.key.compareTo(key);
            if (cmp < 0) {
                n = n.left;
            } else if (cmp > 0) {
                n = n.right;
            } else {
                return n.val;
            }
        }

        return null;
    }

    public void insert(T key, Object val) {
        if (root == null) {
            root = new Node(key, val);
            return;
        }
        put(root, key, val);
    }

    /**
     * Same code handles all cases.
     * <p>
     * (1) Right child red, left child black => rotate left
     * (2) Left child, left-left grandchild red => rotate right
     * (3) Both children are red => flip colors
     *
     * @param h
     * @param key
     * @param val
     * @return
     */
    private Node put(Node h, T key, Object val) {
        if (h == null) {
            return new Node(key, val, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            rotateLeft(h);
        }

        if (isRed(h.left) && h.left.left != null && isRed(h.left.left)) {
            rotateRight(h);
        }

        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        return h;
    }

    /**
     * For example, suppose the link between A and C is red, then node A's color is red
     * * * C
     * * ╱
     * A (R)
     *
     * @param x
     * @return
     */
    private boolean isRed(Node x) {
        // null links are black
        return x != null && x.color == RED;
    }

    /**
     * Orient a (temporarily) right-leaning red link to lean left.
     * <p>
     * E.g.,
     * Before:
     * h
     * * ╲
     * * * x (R)
     * <p>
     * After:
     * * * x
     * * ╱
     * h (R)
     *
     * @param h the parent node.
     * @return x new parent node
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    /**
     * Orient a left-leaning red link to (temporarily) lean right.
     * <p>
     * E.g.,
     * Before:
     * * * h
     * * ╱
     * x (R)
     * <p>
     * After:
     * h
     * * ╲
     * * * x (R)
     *
     * @param h the parent node.
     * @return x new parent node
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    /**
     * Recolor to split a (temporary) 4-node.
     * <p>
     * E.g.,
     * Before:
     * * * h
     * * ╱   ╲
     * x (R)   x (R)
     * <p>
     * After:
     * * * h (R)
     * * ╱   ╲
     * x (B)   x (B)
     *
     * @param h
     */
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
}
