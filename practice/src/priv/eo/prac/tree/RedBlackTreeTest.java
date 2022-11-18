package priv.eo.prac.tree;

import org.junit.jupiter.api.Test;

/**
 * @author omeOmega
 * @ClassName RedBlackTreeTest.java
 * @Description TODO
 * @createTime 2022年11月08日
 */
public class RedBlackTreeTest {
    @Test
    void testRedBlackTree() {
        RedBlackTree<Integer, String> redBlackTree = new RedBlackTree<>();
        redBlackTree.put(1, "张三");
        redBlackTree.put(2, "李四");
        redBlackTree.put(3, "王五");
        System.out.println(redBlackTree.get(1));
        System.out.println(redBlackTree.get(2));
        System.out.println(redBlackTree.get(3));
    }
}
