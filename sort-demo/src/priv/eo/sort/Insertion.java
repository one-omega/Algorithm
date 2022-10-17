package priv.eo.sort;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Insertion.java
 * @Description 插入排序
 * @createTime 2022年10月17日 13:11
 */
public class Insertion {
    public static void sort(int[] a) {
        int len = a.length;
        for (int i = 1; i < len; ++i) {
            int temp = a[i];
            int j = i;
            for (; j > 0 && a[j-1] > temp; --j) {
                a[j] = a[j-1];
            }
            a[j] = temp;
        }
    }
}
