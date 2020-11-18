package job_1;

/**
 * 最小重量机器
 *
 * @author macforsong
 * @version 1.0
 */
public class LowestWeight {
    public static final int max = Integer.MAX_VALUE;

    int n1 = 3;
    int[] factory = new int[n1];
    int[] bestF = new int[n1];
    int cc = 0;
    int cw = 0;
    int bestW = max;

    /**
     * 回溯法求解bestF
     * @param t（当前层数）
     * @param m（工厂个数）
     * @param cost（最大花费）
     * @param w（重量矩阵）
     * @param c（价格矩阵）
     */
    public void machine(int t, int m, int cost, int[][] w, int[][] c) {
        if (t >= n1) {
            bestF = factory.clone();
            bestW = cw;
        } else {
            for (int i = 0; i < m; i++) {
                boolean repeated = false;
                for (int j = 0; j < t; j++) {// 判断是否重复
                    if (factory[j] == i) {
                        repeated = true;
                        break;
                    }
                }
                if (cc + c[t][i] < cost && !repeated) {// 约束函数
                    if (cw + w[t][i] < bestW) {// 界函数
                        cc += c[t][i];
                        cw += w[t][i];
                        factory[t] = i;
                        machine(t + 1, m, cost, w, c);
                        cw -= w[t][i];// 还原现场
                        cc -= c[t][i];
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        LowestWeight lowestWeight = new LowestWeight();
        int[][] w = {{1, 2, 3,},
                {3, 2, 1},
                {2, 3, 2}
        };
        int[][] c = {{1, 2, 3,},
                {5, 4, 2},
                {2, 1, 2}
        };
        lowestWeight.machine(0, 3, 7, w, c);// n1=3
        System.out.print("factory:");
        for (int f : lowestWeight.bestF) {
            System.out.print(f + 1 + " ");
        }
        System.out.println();
        System.out.print("bestw:");
        System.out.println(lowestWeight.bestW);
    }
}
