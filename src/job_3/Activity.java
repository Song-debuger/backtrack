package job_3;

public class Activity {//该类表示一个活动，属性包括开始时间，结束时间和占用时间
    private int start;
    private int end;
    private int time;

    public Activity(int start, int end) {
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
