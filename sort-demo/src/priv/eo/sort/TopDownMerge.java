package priv.eo.sort;

import java.util.Arrays;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Merge.java
 * @Description TODO
 * @createTime 2022年10月17日 13:23
 */
public class TopDownMerge {
    private static int[] aux;

    public static void sort(int[] a) {
        aux = new int[a.length];
        partition(a, 0, a.length-1);
    }

    private static void partition(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        partition(a, left, mid);
        partition(a, mid+1, right);
        merge(a, left, mid, right);
    }

    private static void merge(int[] a, int left, int mid, int right) {
        int i = left, j = mid + 1;
        for (; i <= right; ++i) {
            aux[i] = a[i];
        }
        i = left;
        for (int k = left; k <= right; ++k) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > right) {
                a[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }
}
