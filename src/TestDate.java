import java.util.Hashtable;

/**
 * Created by shir.cohen on 12/30/2017.
 */
public class TestDate {

    public static void main(String[] args) {
        CalenderDate yesterday = new CalenderDate(2014, 5, 7);
        CalenderDate today = new CalenderDate(2014, 7, 5);
        System.out.println(yesterday); // today is NOT earlier than tomorrow
        System.out.println(today); // today is NOT earlier than tomorrow
        System.out.println(today.equals(yesterday)); // today is NOT earlier than tomorrow

        // Create a hash map
        Hashtable calender = new Hashtable();
        calender.put(today, "bla bla");
        System.out.println(calender.get(today)); // today is NOT earlier than tomorrow
        calender.put(today, "bla bla2");
        System.out.println(calender.get(today)); // today is NOT earlier than tomorrow

    }
}
