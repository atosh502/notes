import java.util.*;
import java.io.*;

// Implementation of construction of segment tree 
// and finding min range query

// Run here: https://repl.it/@AashutoshPoudel/segment-tree-min-range-query

class MinRangeSegmentTree {

  // I did this example in order to understand segment
  // https://www.youtube.com/watch?v=ZBHKZF5w4YU
  // tree and ultimately to solve the bracket
  // matching problems

  public static boolean isPowerOfTwo(int n){
    // https://www.geeksforgeeks.org/program-to-find-whether-a-no-is-power-of-two/
    return (n != 0) & ((n & (n - 1)) == 0);
  }

  public static int nextPowerOfTwo(int n){
    // https://www.geeksforgeeks.org/smallest-power-of-2-greater-than-or-equal-to-n/

    int bits = 0;
    while (n > 0){
      bits++;
      n = n >> 1;
    }
    return 1 << bits;
  }

  public static void constructSegmentTree(int[] ar, int[] segment, int left, int right, int pos){

    // for minimum range query
    // ar - the original array 
    // segment - the segment tree 
    // left - the left index for the original array
    // right -  the right index for the original array
    // pos - the indexing for the segment tree

    // we reach the end of the segment tree i.e. left == right
    if (left == right){
      System.out.println("left == right, so segment[" + pos + "] = ar[" + left + "]");
      System.out.println("\twhere ar[" + left + "] = " + ar[left] + "\n");
      segment[pos] = ar[left];
      return;
    }

    int mid = (left + right) / 2;

    
    System.out.println("Inside constructSegmentTree() function");
    System.out.println("\tleft = " + left + " right = " + right + " mid = " + mid + " pos = " + pos + "\n");

    // go into the left and right subtree 
    constructSegmentTree(ar, segment, left, mid, 2 * pos + 1);
    constructSegmentTree(ar, segment, mid+1, right, 2 * pos + 2);

    System.out.println("segment[" + pos + "] = min(segment[" + (2 * pos + 1) + "], segment[" + (2 * pos + 2) + "])");
    System.out.println("\twhere segment[2*pos+1] = " + segment[2*pos+1] + ", segment[2*pos+2] = " + segment[2*pos+2] + "\n");

    segment[pos] = Math.min(segment[2 * pos + 1], segment[2 * pos + 2]);
  }

  public static int query(int left, int right, int qleft, int qright, int[] segment, int pos){
    
    // left - the left index of the array
    // right - the right index of the array
    // qleft - the leftmost index of query
    // qright - the rightmost index of the query
    // segment - the actual segment tree
    // pos - this actually indexes the segment tree

    // the case of total overlap of [left, right] inside the range query
    if (left >= qleft && right <= qright){

      System.out.println("Total overlap: [left,right]=[" + left + "," + right + "] [qleft,qright]=[" + qleft + "," + qright + "]");
      System.out.println("segment[pos]=" + segment[pos] + " is returned, where pos=" + pos);
      System.out.println();

      return segment[pos];
    }

    // no overlap
    if (right < qleft || left > qright){

      System.out.println("No overlap: [left,right]=[" + left + "," + right + "] [qleft,qright]=[" + qleft + "," + qright + "]");
      System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE + " is returned");
      System.out.println();

      return Integer.MAX_VALUE;
    }

    // partial overlap
    int mid = (left + right) / 2;

    System.out.println("Partial overlap: [left,right]=[" + left + "," + right + "] [qleft,qright]=[" + qleft + "," + qright + "]");
    System.out.println("mid = " + mid + " pos = " + pos);
    int q1 = query(left, mid, qleft, qright, segment, 2*pos+1);
    int q2 = query(mid+1, right, qleft, qright, segment, 2*pos+2);
    System.out.println("min of segment[2*pos+1] = " + q1 + " and segment[2*pos+2] = " + q2 + " is " + Math.min(q1, q2));
    System.out.println();

    return Math.min(q1, q2);

  }

  public static void main(String[] args) {
    int[] ar = {-1, 2, 4, 0};
    int len = ar.length;   // the lenght of array
    int n; // the size of segemnt tree / array
    if (isPowerOfTwo(len) == true){
      n = 2 * len - 1;
    } else {
      n = 2 * nextPowerOfTwo(len) - 1;
    }

    // now the function for constructing the actual segment tree
    
    // initially we want the segment tree to be filled with some max values Integer.MAX_VALUE
    int[] segment = new int[n];
    for (int i = 0; i < n; i++){
      segment[i] = Integer.MAX_VALUE;
    }

    constructSegmentTree(ar, segment, 0, len-1, 0);

    System.out.println("The resulting segment tree is: " + Arrays.toString(segment));
    System.out.println();

    // now for performing min range queries in the constructed segment tree
    int result = query(0, len-1, 0, 2, segment, 0);
    System.out.println("Min elements between [0, 2] is " + result);
  }
}
