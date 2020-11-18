package job_5;

/**
 * 有趣的高精度数
 *
 * @author macforsong
 * @version 1.0
 */
public class InterHPNumber {
    public static final int MAX = 10000;
    public static int[] A = new int[MAX];//存储当前值
    public static int[] B = new int[MAX];//存储最优值

    public static void main(String[] args) {

        InterHPNumber interHPNumber = new InterHPNumber();
        interHPNumber.backTrackingMaxNumber(1);
        for (int k = 1; B[k] != -1; k++)
            System.out.print(B[k]);
        System.out.print("\n");

    }

    /**
     * 回溯法求解
     *
     * @param t
     */
    public void backTrackingMaxNumber(int t) {
        if (bigThan(A, B)) {
            B = A.clone();
        }
        int j;
        if (t == 1) {//第一位不能取0
            j = 1;
        } else j = 0;//其他位可以
        for (; j <= 9; j++) {
            A[t] = j;
            if (OK(A, t)) {
                backTrackingMaxNumber(t + 1);
            }
            A[t] = -1;
        }
    }

    /**
     * 检查第i位是否满足条件
     *
     * @param a
     * @param t
     * @return
     */
    public boolean OK(int[] a, int t) {
        int r = 0;
        for (int i = 1; i <= t; i++) {//检查第i位是否满足条件
            r = r * 10 + a[i];
            r = r % t; //整数倍乘以10没用故只用余数， 防止溢出
        }
        r = r % t;
        if (r == 0) return true;
        return false;
    }

    /**
     * 判断A数值是否大于B
     *
     * @param a
     * @param b
     * @return
     */
    public boolean bigThan(int[] a, int[] b) {
        int asize = 0;//a的长度
        int bsize = 0;//b的长度
        if (a[1] == 0) return false;
        for (int i = 1; i < MAX; i++) {//获得当前高精度数的位数
            if (a[i] == -1) {
                asize = i - 1;
                break;
            }
        }
        for (int i = 1; i < MAX; i++) {//获得当前最优数的位数

            if (b[i] == -1) {
                bsize = i - 1;
                break;
            }
        }
        if (asize < bsize)
            return false;
        if (asize > bsize)
            return true;
        for (int i = 1; i < asize; i++) {
            if (a[i] > b[i])
                return true;
            if (a[i] < b[i])
                return false;

        }
        return false;

    }

}
