package priv.eo.lab1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
//    private QuickFindUF unionUFWithTop;
//    private QuickFindUF unionUFWithTopAndBottom;
    private WeightedQuickUnionUF unionUFWithTop; //不包含virtual bottom
    private WeightedQuickUnionUF unionUFWithTopAndBottom; //包含2个虚节点
    private int n;
    private boolean[] isOpen;
    private int virtualTop = 0;
    private int virtualBottom;
    private int openSitesCnt;

    // create N-by-N grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n必须大于0");
        }
        this.n = n;
        virtualBottom = n * n + 1;
        unionUFWithTop = new WeightedQuickUnionUF(n * n + 1);
        unionUFWithTopAndBottom = new WeightedQuickUnionUF(n * n + 2);
//        unionUFWithTopAndBottom = new QuickFindUF(n * n + 2);
//        unionUFWithTop = new QuickFindUF(n * n + 1);
        isOpen = new boolean[n * n + 2];
        isOpen[virtualTop] = true;
        isOpen[virtualBottom] = true;
    }

    // Check if array index is out of bounds
    private void checkIndex(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n) {
            throw new IndexOutOfBoundsException("数组下标越界");
        }
    }

    // 2D index to 1D index
    private int getIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    // search around a point
    private void connectAround(int row, int col) {
        int index = getIndex(row, col);
        if (row == 1) {
            unionUFWithTopAndBottom.union(virtualTop, index);
            unionUFWithTop.union(virtualTop, index);
        }
        if (row == n) {
            unionUFWithTopAndBottom.union(virtualBottom, index);
        }
        int[] direction = new int[]{-1, 0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int x = row + direction[i];
            int y = col + direction[i + 1];
            if (x >= 1 && x <= n && y >= 1 && y <= n && isOpen(x, y)) {
                unionUFWithTopAndBottom.union(index, getIndex(x, y));
                unionUFWithTop.union(index, getIndex(x, y));
            }
        }
    }

    public int getOpenSitesCnt() {
        return openSitesCnt;
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        checkIndex(i, j);
        if (!isOpen(i, j)) {
            isOpen[getIndex(i, j)] = true;
            ++openSitesCnt;
            connectAround(i, j);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkIndex(i, j);
        return isOpen[getIndex(i, j)];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkIndex(i, j);
        return unionUFWithTop.find(getIndex(i, j)) == unionUFWithTop.find(virtualTop);
    }

    // does the system percolate?
    public boolean percolates() {
        return unionUFWithTopAndBottom.find(virtualTop)
                == unionUFWithTopAndBottom.find(virtualBottom);
    }

    // test client, optional
    public static void main(String[] args) {
        StdOut.println("Hello,Percolation");
    }
}

