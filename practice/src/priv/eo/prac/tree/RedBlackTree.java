package priv.eo.prac.tree;

/**
 * @author omeOmega
 * @ClassName RedBlackTree.java
 * @Description 红黑树的实现
 * @createTime 2022年11月08日
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private int N;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key; // 键
        private Value val; // 值
        private Node left, right; // 指向子树的链接
        private boolean color;

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private boolean isRed(Node h) {
        if (h == null) {
            return BLACK;
        }
        return h.color;
    }

    //左旋
    private Node rotateLeft(Node h) {
        //命名h的右子树为x
        Node x = root.right;
        //将x的左子树改为h的右子树
        h.right = x.left;
        //将h改为x的左子树
        x.left = h;
        //将x的节点颜色改为h节点的颜色
        x.color = h.color;
        //h的结点颜色改为红色
        h.color = RED;
        return x;
    }

    //右旋
    private Node rotateRight(Node h) {
        //命名h的左子树为x
        Node x = h.left;
        //将x的右子树改为h的左子树
        h.left = x.right;
        //x的右子树指向h
        x.right = h;
        //x的结点颜色改为h的节点颜色
        x.color = h.color;
        //h的节点颜色改为红色
        h.color = RED;
        return x;
    }

    //颜色反转
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    //返回树的节点个数
    private int size() {
        return N;
    }

    //插入节点
    public void put(Key key, Value val) {
        // 查找key，找到则更新其值，否则为它新建一个结点
        root = put(root, key, val);
        root.color = BLACK;
    }

    //在指定的子树里插入节点
    private Node put(Node h, Key key, Value val) {
        if (h == null) {// 标准的插入操作，和父结点用红链接相连
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
        //左连接为黑色，右连接为红色
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        //左连接为红色，左孩子的左连接还是红色
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        //左连接和右连接均为红色
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        return get(root, key);
    }

    //在指定的子树里查找元素
    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.val;
        }
    }
}
