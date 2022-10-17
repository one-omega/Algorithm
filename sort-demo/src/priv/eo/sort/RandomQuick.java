package priv.eo.sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Quick.java
 * @Description TODO
 * @createTime 2022年10月17日 15:30
 */
public class RandomQuick {

    public static void sort(int[] a) {
        StdRandom.shuffle(a);
        quickSort(a, 0, a.length-1);
    }

    private static void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int label = partition(a, left, right);
        quickSort(a, left, label-1);
        quickSort(a, label+1, right);
    }

    private static int partition(int[] a, int left, int right) {
        int i = left, j = right;
        int temp = a[left];
        while (i < j) {
            while (a[j] >= temp && i < j) {
                --j;
            }
            while (a[i] <= temp && i < j) {
                ++i;
            }
            swap(a, i, j);
        }
        swap(a, left, i);
        return i;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
}
