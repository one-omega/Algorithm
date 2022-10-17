package priv.eo.sort;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName BottomUpMerge.java
 * @Description TODO
 * @createTime 2022年10月17日 16:48
 */
public class BottomUpMerge {

    private static int[] aux;

    public static void sort(int[] a) {
        int n = a.length;
        aux = new int[n];
        for (int sz = 1; sz < n; sz *= 2) {
            for (int i = 0; i < n - sz; i += (sz * 2)) {
                merge(a, i, i + sz - 1, Math.min(i + sz * 2 -1, n - 1));
            }
        }
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
