package priv.eo.sort;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SortTest.java
 * @Description 测试类
 * @createTime 2022年10月17日 13:16
 */
public class SortTest {

    private static int[] a = {5,2,4,7,8,9,4,1,2,6,4,3,5,8,2,4,0,4,2,1};

    private static double[][] time = new double[5][12];
    private static double[][] memory = new double[5][12];

    @Test
    void insertionTest() {
        Insertion.sort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    void topDownMergeTest() {
        TopDownMerge.sort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    void bottomUpMergeTest() {
        BottomUpMerge.sort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    void randomQuickTest() {
        RandomQuick.sort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    void quickWithD3PTest() {
        D3PQuickSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void start(int i, int j) {
        //得到程序开始时的系统时间（纳秒级，最终转化毫秒，保留小数点后两位）
        time[i][j] = System.nanoTime();
//        time[i][j] = System.currentTimeMillis();
        //得到虚拟机运行、程序开始执行时jvm所占用的内存。
        Runtime runtime = Runtime.getRuntime();
        memory[i][j] = runtime.freeMemory();
    }

    // 1毫秒(ms) = 1000微秒(us) = 1000 000纳秒(ns)
    // 1 KB = 1024 byte;
    public static void end(int i, int j) {
        //得到程序执行完毕时的系统时间（纳秒级）
        time[i][j] = (System.nanoTime() - time[i][j]) / 1000000;
//        time[i][j] = (System.currentTimeMillis() - time[i][j]);
        //得到虚拟机运行、所要测试的执行代码执行完毕时jvm所占用的内存（byte）。
        Runtime runtime = Runtime.getRuntime();
        memory[i][j] = (memory[i][j] - runtime.freeMemory()) / 1024;
//        System.out.println("---------------您的代码执行时间为：" + time.substring(0,time.equals("0.0") ? 1 : (time.indexOf(".")+3)) + " ms, 消耗内存：" + memory.substring(0,memory.equals("0.0") ? 1 : (memory.indexOf(".")+3)) + " M + !---------------");
    }

    public static void info(int i) {
        System.out.println("time:");
        double totalTime = 0;
        double totalMemory = 0;
        for (int j = 2; j < 12; j++) {
            totalTime += time[i][j];
            System.out.print(time[i][j] + "ms ");
        }
        double meanTime = totalTime / 10;
        System.out.println('\n' + "mean time:" + meanTime + "ms");
        System.out.println("memory:");
        for (int j = 2; j < 12; j++) {
            totalMemory += memory[i][j];
            System.out.print(memory[i][j] + "KB ");
        }
        double meanMemory = totalMemory / 10;
        System.out.println('\n' + "mean memory:" + meanMemory + "KB");
    }

    public static void main(String[] args) {
        System.out.println("数据规模：");
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        a = new int[length];
        for (int i = 0; i < length; ++i) {
            a[i] = StdRandom.uniform(1000);
        }
        Arrays.sort(a);
        //Insertion
        System.err.println("Insertion:");
        int[] nums = new int[length];
        for (int i = 0; i < 12; ++i) {
            nums = Arrays.copyOf(a, length);
            start(0, i);
            Insertion.sort(nums);
            end(0, i);
        }
        info(0);
        //TopDownMerge
        System.err.println("TopDownMerge:");
        for (int i = 0; i < 12; ++i) {
            nums = Arrays.copyOf(a, length);
            start(1, i);
            TopDownMerge.sort(nums);
            end(1, i);
        }
        info(1);
        //BottomUpMerge
        System.err.println("BottomUpMerge:");
        for (int i = 0; i < 12; ++i) {
            nums = Arrays.copyOf(a, length);
            start(2, i);
            BottomUpMerge.sort(nums);
            end(2, i);
        }
        info(2);
        //RandomQuick
        System.err.println("RandomQuick:");
        for (int i = 0; i < 12; ++i) {
            nums = Arrays.copyOf(a, length);
            start(3, i);
            RandomQuick.sort(nums);
            end(3, i);
        }
        info(3);
        //D3PQuickSort
        System.err.println("D3PQuickSort:");
        for (int i = 0; i < 10; ++i) {
            nums = Arrays.copyOf(a, length);
            start(4, i);
            D3PQuickSort.sort(nums);
            end(4, i);
        }
        info(4);
    }
}
