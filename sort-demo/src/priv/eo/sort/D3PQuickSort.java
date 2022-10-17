package priv.eo.sort;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName D3PQuickSort.java
 * @Description Dijkstra 3-路划分快速排序（Quicksort with Dijkstra 3-way Partition，QD3P）
 * @createTime 2022年10月17日 16:51
 */
public class D3PQuickSort {

    public static void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int base = a[left];
        int lt = left, gt = right;
        int i = left + 1;
        while (i <= gt) {
            if (a[i] < base) {
                RandomQuick.swap(a, i++, lt++);
            } else if (a[i] == base) {
                ++i;
            } else {
                RandomQuick.swap(a, i, gt--);
            }
        }
        sort(a, left, lt - 1);
        sort(a, gt + 1, right);
    }
}
