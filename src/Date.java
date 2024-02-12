import java.util.Calendar;

/**
 * Manages calendar dates with functions for setting, comparing, and validating.
 * Supports leap year handling.
 * @author Woogyeom Sim, Aravind Chundu
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constructs a Date object with specified year, month, and day.
     *
     * @param month The month of the date.
     * @param day   The day of the date.
     * @param year  The year of the date.
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Returns the year of this date.
     *
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of this date.
     *
     * @param year The year to set.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the month of this date.
     *
     * @return The month.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the month of this date.
     *
     * @param month The month to set.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Returns the day of this date.
     *
     * @return The day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day of this date.
     *
     * @param day The day to set.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Converts this Date object to a String in mm/dd/yyyy format.
     *
     * @return A string representation of this date.
     */
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Checks if this date is valid.
     * A date before 1900 is considered invalid. Also checks for valid month,
     * day considering leap years.
     *
     * @return true if this date is valid, false otherwise.
     */
    public boolean isValid() {
        if (year < 1900) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }

        Calendar today = Calendar.getInstance();

        Calendar input = Calendar.getInstance();
        input.setLenient(false); // only valid calendar date
        input.set(year, month - 1, day);

        if (input.after(today)) {
            return false; // today or a future date
        }

        try {
            input.getTime();
            return true;
        } catch (Exception e) {
            return false; // not a valid calendar date
        }
    }

    /**
     * Compares this Date object with another Date object for order.
     * Returns a negative integer, zero, or a positive integer as this Date is less than,
     * equal to, or greater than the specified Date.
     *
     * @param date The Date object to be compared.
     * @return the value 0 if the argument Date is equal to this Date; a value less than 0 if this Date
     * is before the Date argument; and a value greater than 0 if this Date is after the Date argument.
     */
    public int compareTo(Date date) {
        if (this.year != date.year) {
            return this.year - date.year;
        }
        if (this.month != date.month) {
            return this.month - date.month;
        }
        return this.day - date.day;
    } // returns -1 if this is earlier
      // returns 0 if it's the same day
      // returns 1 if this is later

    /**
     * Main method for testing the Date class functionality.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args){
        Date dateTest1 = new Date(13, 15, 2023);
        Date dateTest2 = new Date(2, 29, 2023);
        Date dateTest3 = new Date(4, 31, 2023);
        Date dateTest4 = new Date(4, 29, 2025);
        Date dateTest5 = new Date(12, 31, 1899);
        Date dateTest6 = new Date(2, 29, 2000);
        Date dateTest7 = new Date(12, 31, 2023);

        System.out.println(dateTest1.isValid()); //False - Invalid date - month out of range
        System.out.println(dateTest2.isValid()); //False - Invalid date - Day beyond range for Feb in non-leap year
        System.out.println(dateTest3.isValid()); //False - Invalid date - Day beyond range for a month
        System.out.println(dateTest4.isValid()); //False - Invalid date - Day is in the future
        System.out.println(dateTest5.isValid()); //False - Invalid date - Year is before 1900
        System.out.println(dateTest6.isValid()); //True - Valid Leap year date
        System.out.println(dateTest7.isValid()); //True - Valid day since it is in a 31-day month


    }
}
