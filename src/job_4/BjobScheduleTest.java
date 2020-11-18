package job_4;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BjobScheduleTest {

    @Test
    public void testBjobSchedule() throws IOException {
        int[][] m = {
                {2,1},
                {3,1},
                {2,3}
        };
        int bestx[] = {1,3,2};
        int n = 3;
        BjobSchedule bjobSchedule = new BjobSchedule(m,n);

        int bestf = bjobSchedule.getBestf();
        int[] bx = bjobSchedule.getBestx();

        for (int j=0;j<n;j++){
            Assert.assertEquals(bestx[j],bx[j]);
        }
        Assert.assertTrue(bestf == 18);
    }
}
