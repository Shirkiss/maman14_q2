import java.io.Serializable;

import static java.time.Year.isLeap;

/**
 * CalenderDate.java
 * Represent a date such as "01/01/2018"
 *
 * @author Shir Cohen
 */
public class CalenderDate implements Serializable {
    private int day;
    private int month;
    private int year;

    CalenderDate(int year, int month, int day) throws IllegalArgumentException {
        if (!isValid(year, month, day)) throw new IllegalArgumentException();
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Create hash code to CalenderDate object based on the String it represent
     *
     * @return hash code number
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Check if 2 dates are equals
     *
     * @return true if the dates are equal
     */
    @Override
    public boolean equals(Object obj) {
        return (this.hashCode() == obj.hashCode());
    }

    /**
     * Getter for day
     *
     * @return day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Getter for month
     *
     * @return month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Getter for year
     *
     * @return year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * return a string representation
     *
     * @return
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (this.day < 10) s.append("0");
        s.append(String.valueOf(this.day));
        s.append("-");
        if (this.month < 10) s.append("0");
        s.append(String.valueOf(this.month));
        s.append("-");
        s.append(String.valueOf(this.year));
        return s.toString();
    }

    /**
     * Check if given year/month/day is valid
     *
     * @param year
     * @param month
     * @param day
     * @return true if it is valid date
     */
    private static boolean isValid(int year, int month, int day) {
        if (year < 0) return false;
        if ((month < 1) || (month > 12)) return false;
        if ((day < 1) || (day > 31)) return false;
        switch (month) {
            case 1:
                return true;
            case 2:
                return (isLeap(year) ? day <= 29 : day <= 28);
            case 3:
                return true;
            case 4:
                return day < 31;
            case 5:
                return true;
            case 6:
                return day < 31;
            case 7:
                return true;
            case 8:
                return true;
            case 9:
                return day < 31;
            case 10:
                return true;
            case 11:
                return day < 31;
            default:
                return true;
        }
    }
}
