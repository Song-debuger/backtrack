package job_6;

/**
 * 名画陈列馆问题
 *
 * @author macforsong
 * @version 2.0
 */
public class ExhibitionHall {
    int n, m;
    int[][] a;// n*m，警卫机器人位置
    int[][] besta;//n*m, 当前最优警卫机器人位置
    boolean[][] b;// n*m，每个陈列室的监视情况
    int x;//当前解
    int bestx = Integer.MAX_VALUE; //当前最优解
    boolean exist = false;


    /**
     * 构造函数
     *
     * @param n
     * @param m
     */
    public ExhibitionHall(int n, int m) {
        super();
        this.n = n;
        this.m = m;
        this.a = new int[n][m];
        this.b = new boolean[n][m];
        this.besta = new int[n][m];
    }

    /**
     * 约束函数，检测当前位置是否被监管
     *
     * @param t 行
     * @param i 列
     * @return 0/1,t行i列是否被监管
     */
    public boolean check(int t, int i) {
        if (t - 1 < 0 && i - 1 < 0)// 左上角
            return !b[t][i];
        if (t - 1 < 0 && i + 1 >= m)// 右上角
            return !(b[t][i] || b[t][i - 1]);
        if (t - 1 < 0)// 第0行
            return !(b[t][i] || b[t][i - 1] || b[t][i + 1]);
        if (i - 1 < 0)// 第0列
            return !(b[t][i] || b[t - 1][i] || b[t][i + 1]);
        if (i + 1 >= m)// 最右列
            return !(b[t][i] || b[t - 1][i] || b[t][i - 1]);

        return !(b[t][i] || b[t - 1][i] || b[t][i - 1] || b[t][i + 1]);
    }

    /**
     * 回溯法求解，更新最优解
     *
     * @param t
     */
    public void backtrack(int t, int i) {
        if (t >= n) {//到达叶子节点
            boolean flag = true;
            for (int j = 0; j < m; j++) {//判断最后一行是否每个陈列室都被监视
                if (!b[t - 1][j]) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                exist = true;
                x = 0;
                for (int k = 0; k < n; k++) {//记录当前解警卫个数
                    for (int j = 0; j < m; j++) {
                        if (a[k][j] == 1) {
                            x++;//警卫个数
                        }
                    }
                }

                if (x < bestx) {//跟新当前最优解
                    bestx = x;
                    for (int k = 0; k < n; k++) {//更新当前最优解矩阵
                        for (int j = 0; j < m; j++) {
                            besta[k][j] = a[k][j];
                        }
                    }
                }
            }
        } else {
            if (check(t, i)) {//约束函数
                a[t][i] = 1;
                b[t][i] = true;
                if (t - 1 >= 0)
                    b[t - 1][i] = true;
                if (t + 1 < n)
                    b[t + 1][i] = true;
                if (i - 1 >= 0)
                    b[t][i - 1] = true;
                if (i + 1 < m)
                    b[t][i + 1] = true;
                if (i < m - 3) {//t行未满
                    backtrack(t, i + 3);//i+1已被监控，直接搜索i+3
                } else {//t行已放满
                    if (t > 0) { //当t行不能再放警卫时，检查t-1行是否都被监管
                        boolean flag = true;
                        for (int j = 0; j < m; j++) {
                            if (b[t - 1][j] == false) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {// t-1行放满，搜索下一行。
                            backtrack(t + 1, 0);
                        }
                    } else {// 第0行不需要判断界
                        backtrack(t + 1, 0);
                    }
                }
                a[t][i] = 0;// 还原现场
                b[t][i] = false;
                if (t - 1 >= 0)
                    b[t - 1][i] = false;
                if (t + 1 < n)
                    b[t + 1][i] = false;
                if (i - 1 >= 0)
                    b[t][i - 1] = false;
                if (i + 1 < m)
                    b[t][i + 1] = false;
            }
            a[t][i] = 0;
            if (i < m - 1) {//t行未放满
                backtrack(t, i + 1);
            } else {// t行已放满
                if (t > 0) {//当t行已被监管，检查t-1行是否都被监管
                    boolean flag = true;
                    for (int j = 0; j < m; j++) {
                        if (b[t - 1][j] == false) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {// t-1行放满，搜索下一行，否则剪枝
                        backtrack(t + 1, 0);
                    }
                } else {// 第0行不需要判断界
                    backtrack(t + 1, 0);
                }
            }

        }
    }

    /**
     * 输出当前最优解矩阵
     */
    public void outbesta() {
        System.out.println();
        for (int i = 0; i < n; i++) {//输出当前最优解矩阵
            for (int j = 0; j < m; j++) {
                System.out.print(besta[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ExhibitionHall exhibitionHall = new ExhibitionHall(4, 4);
        exhibitionHall.backtrack(0, 0);
        if (exhibitionHall.exist) {
            System.out.println(exhibitionHall.bestx);
            exhibitionHall.outbesta();
        } else
            System.out.println("No Solution!");
    }
}
