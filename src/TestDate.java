/**
 * Created by shir.cohen on 12/30/2017.
 */
public class TestDate {

    public static void main(String[] args){
        MyDate yesterday = new MyDate(2014, 5, 7);
        MyDate today = new MyDate(2014, 7, 5);
        System.out.println(yesterday); // today is NOT earlier than tomorrow
        System.out.println(today); // today is NOT earlier than tomorrow
        System.out.println(today.equals(yesterday)); // today is NOT earlier than tomorrow

    }
}
