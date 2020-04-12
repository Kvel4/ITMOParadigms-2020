package search;


public class BinarySearch {
    // Pre: a!= null && a[] : i<j -> a[i] >= a[j] &&
    // (l, r] - полуинтервал поиска  && -1 <= l < ind <= r <= a.length
    // Post: x in a ? min i : a[i] <= x : a.length
    private static int functionBinarySearch(int x, int[] a, int l, int r) {
        if (r - l == 1) {
            // r - l == 1 && l < ind <= r ==> ind == r <= a.length ==>
            // x in a ? r == min i : a[i] <= x : r == a.length
            return r;
        }
        int m = (r + l) / 2;
        // r-l != 1 && m == (r+l)/2 && l < m < r
        if (a[m] <= x) {
            // a[m] <= x && arr is sorted ==> for i: (m...r] : a[i] <= a[m] <= x ==>
            // (min i : a[i] <= x) <= m
            // m == (r+l)/2 ==>
            // r = 2m - l && |r - l| == |2m - l - l| == 2|m - l| ->
            // -> длина (l, m] в 2 раза меньше (l, r]
            return functionBinarySearch(x, a, l, m);
        }
        // a[m] > x && arr is sorted ==> for i: (l...m) : a[i] >= a[m] > x ==>
        // (min i : a[i] <= x) > m
        // m == (r+l)/2
        // l = 2m - r && |r - l| == |r - 2m -(-r)| == 2|r - m|
        // -> длина (m, r] в 2 раза меньше (l, r]
        return functionBinarySearch(x, a, m, r);
    }

    // Pre: a!= null && a[] : i<j -> a[i] >= a[j] &&
    // Post: x in a ? min i : a[i] <= x : a.length
    private static int iterativeBinarySearch(int x, int[] a) {
        int l = -1, r = a.length;
        while (r - l > 1) {
            // r-l != 1 && (l, r] - полуинтервал поиска  && -1 <= l < ind <= r <= a.length
            int m = (r + l) / 2;
            // m == (r+l)/2 && l < m < r
            if (a[m] <= x) {
                // a[m] <= x && arr is sorted ==> for i: (m...r] : a[i] <= a[m] <= x ==>
                // (min i : a[i] <= x) <= m
                r = m;
                // r = m == (r'+l)/2 ==>
                // r' = 2r - l && |r' - l| == |2r - l - l| == 2|r - l| ->
                // -> длина (l, r] в 2 раза меньше (l, r'] && r < r'
            } else {
                // a[m] > x && arr is sorted ==> for i: (l...m) : a[i] >= a[m] > x ==>
                // (min i : a[i] <= x) > m
                l = m;
                // l = m == (r+l')/2
                // l' = 2l - r && |r - l'| == |r - 2l -(-r)| == 2|r - l|
                // -> длина (l, r] в 2 раза меньше (l', r] && l > l'
            }
        }
        // r - l == 1 && l < ind <= r ==> ind == r <= a.length ==>
        // x in a ? r == min i : a[i] <= x : r == a.length
        return r;
    }

    // Pre: String[] == console input && String[] not empty &&
    // String[] firstElement == string representation of int x or int x, x == what we find
    // other elements of String[] string representation of int elements or int elements of int[] a, a == where we find
    // Post: x in a ? print(min i : a[i] <= x) : print(a.length)
    public static void main(String[] args) {
        // args == console input
        int x = Integer.parseInt(args[0]);
        // x == integer representation of args[0]
        int[] a = new int[args.length - 1];
        // a == an empty int array
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
            // a[i-1] == integer representation of args[i]
        }
        // a array consist of other(not first) element in args(console input)
//        System.out.println(functionBinarySearch(x, a, -1, a.length));
        System.out.println(iterativeBinarySearch(x, a));
        // result of binary search == x in a ? min i : a[i] <= x : a.length ==>
        // x in a ? print(min i : a[i] <= x) : print(a.length)
    }
}
