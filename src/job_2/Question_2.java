package job_2;

import java.util.Arrays;

/**
 * @author macforsong
 * @version 1.0
 */
public class Question_2 {
    private int n;//用于计算是否满足条件的整数
    private int[] s;//大写字母构成的初始数组
    private boolean[] vis;//用于判断该字符是否已经选择过
    private int[] x = new int[5];//当前解
    private int[] bestx = {-1, -1, -1, -1, -1};//最优解
    private char[] result = new char[5];//最后结果

    public void backtrack(int i) {
        if (i >= 5) {
            if (check()) {    //若解满足条件，将当前解赋给最优解。由于已经排序，最后的最优解就是字典排序最大的最优解
                for (int j = 0; j < 5; j++) {
                    //System.out.print(x[j]+" ");
                    bestx[j] = x[j];
                }
                //System.out.println();
            }

        }
        if (i < 5) {
            for (int j = 0; j < s.length; j++) {
                if (!vis[j]) {
                    x[i] = s[j];
                    vis[j] = true;
                    backtrack(i + 1);
                    vis[j] = false;
                }

            }
        }
    }

    private boolean check() { //判断目前的解是否满足条件
        double ans = Math.pow(x[0], 1) - Math.pow(x[1], 2) +
                Math.pow(x[2], 3) - Math.pow(x[3], 4) +
                Math.pow(x[4], 5);

        if ((int) ans == n) {
            //System.out.println(ans);
            return true;
        }
        return false;
    }

    public char[] solve(int n, char[] str) {
        char[] noAns = {'n', 'o', ' ', 's', 'o', 'l', 'u', 't', 'i', 'o', 'n',};
        int[] e = {-1, -1, -1, -1, -1};
        for (int i = 0; i < 5; i++)
            bestx[i] = e[i];    //清空bestx,防止上一次计算干扰
        Arrays.sort(str);    //排序，便于找到字典序最大解
        this.n = n;
        s = new int[str.length];
        vis = new boolean[str.length];
        for (int i = 0; i < str.length; i++) {
            s[i] = str[i] - 'A' + 1;//将字母转化为数字
            vis[i] = false;
            //System.out.println(s[i]);
        }
        for (int i = 0; i < 5; i++) {
            x[i] = 0;
        }
        backtrack(0);
        for (int i = 0; i < 5; i++) {
            if (bestx[i] == -1) { //当最优解没有更新时，返回no solution
                return noAns;
            }
            result[i] = (char) (bestx[i] + 'A' - 1);//将字母转化为数字
            //System.out.println(s[i]);
        }
        return result;

    }

    public static void main(String[] args) {
        Question_2 p2 = new Question_2();
        char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
        char[] b = {'Z', 'A', 'Y', 'E', 'X', 'I', 'W', 'O', 'V', 'U'};
        char[] c = {'S', 'O', 'U', 'G', 'H', 'T'};
        char[] d = {'T', 'H', 'E', 'Q', 'U', 'I', 'C', 'K', 'F', 'R', 'O', 'G'};
        System.out.println(p2.solve(1, a));
        System.out.println(p2.solve(11700519, b));
        System.out.println(p2.solve(3072997, c));
        System.out.println(p2.solve(1234567, d));
    }
}
