package job_3;

import java.util.Vector;


public class Question_3 {
    private int n;//活动的数目
    private int max = 300000;//最大不能超过300000
    private Vector<Activty> list = new Vector<Activty>();//总共的活动列表
    private Vector<Activty> acceptActivties;//接受的活动列表
    private Vector<Activty> bestActivties;
    private int ct = 0; //当前占用的总时间
    private int bestT;//最大占用总时间
    private int flag;//用于判断是否应该结束递归

    public void backtrack(int t) {
        flag = 0;
        for (int i = 0; i < n; i++) {
            Activty activty = list.get(i);    //依次取出每一个活动
            if (check(activty)) {            //如果当前活动与现有的安排不冲突，就接受它
                acceptActivties.add(activty);
                ct += activty.getTime();
                backtrack(i);                //递归
                ct -= activty.getTime();        //恢复现场
                acceptActivties.remove(activty);
                flag++;
            }
        }
        if (flag == 0) {            //flag没有改变时，说明已经到最底层了，此时获得了一个解
            if (ct > bestT) {    //当前解与最优解做比较
                bestT = ct;
                bestActivties = acceptActivties;

            }
        }
    }

    public boolean check(Activty b) {    //判断当前活动b与已经接受的活动是否冲突
        if (acceptActivties.isEmpty()) {
            return true;
        }
        for (Activty a : acceptActivties) {
            if ((a.getStart() >= b.getEnd() || a.getEnd() <= b.getStart()) && b.getEnd() <= max) {
            } else {
                return false;
            }
        }
        return true;
        //return false;

    }

    public void solve() {    //初始化部分属性，开始第一次递归
        bestT = 0;
        n = list.size();
        acceptActivties = new Vector<Activty>();
        bestActivties = new Vector<Activty>();
        backtrack(0);
        System.out.println(bestT);
    }

    public static void main(String[] args) {
        Question_3 p3 = new Question_3();
        p3.list.add(p3.new Activty(1, 2));
        p3.list.add(p3.new Activty(3, 5));
        p3.list.add(p3.new Activty(1, 4));
        p3.list.add(p3.new Activty(4, 5));
        p3.solve();
    }

    private class Activty {//该类表示一个活动，属性包括开始时间，结束时间和占用时间
        private int start;
        private int end;
        private int time;

        public Activty(int start, int end) {
            super();
            this.start = start;
            this.end = end;
            this.time = end - start;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getTime() {
            return time;
        }

        public String toString() {
            String str = "开始时间为： " + getStart() + ",结束时间为： " + getEnd();
            return str;

        }

    }
}
