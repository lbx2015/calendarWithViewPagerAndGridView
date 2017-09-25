package necer.ncalendardemo;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMonths() {
        DateTime startDateTime = new DateTime("2017-07-01");
        DateTime endDateTime = new DateTime("2017-08-31");

        int mPageSize = Months.monthsBetween(startDateTime, endDateTime).getMonths();
        System.out.println(mPageSize);
    }
}