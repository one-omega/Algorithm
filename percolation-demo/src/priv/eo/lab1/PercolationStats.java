package priv.eo.lab1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Scanner;

public class PercolationStats {
    private static final double CONFIDENCE_CONST = 1.96;
    private int n;
    private int numOfExp; //实验次数
    private double[] threshold;    //多组渗透阈值
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int n, int numOfExp) {
        if (n <= 0 && numOfExp <= 0) {
            throw new IllegalArgumentException("参数必须大于0");
        }
        this.n = n;
        this.numOfExp = numOfExp;
        threshold = new double[numOfExp];
        for (int i = 0; i < numOfExp; ++i) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                //a random integer uniformly in [a, b)
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            int openSitesCnt = percolation.getOpenSitesCnt();
            threshold[i] = (double) openSitesCnt / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_CONST * stddev() / Math.sqrt(numOfExp);
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_CONST * stddev() / Math.sqrt(numOfExp);
    }

    // test client, described below
    public static void main(String[] args) {
        System.out.println("enter N, T:");
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int T = in.nextInt();
        long startTime = System.currentTimeMillis();
        PercolationStats percolationStats = new PercolationStats(N, T);
        StdOut.println("mean                    = " + percolationStats.mean());
        long endTime = System.currentTimeMillis();
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ","
                + percolationStats.confidenceHi() + "]");
        StdOut.println("the cost of time        = " + (endTime - startTime) + "ms");
    }
}

