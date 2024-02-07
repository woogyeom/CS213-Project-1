public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public boolean isValid(){
        //valid day
        if (day > 31 || day < 1) {
            return false;
        }
        if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        }
        if (day > 29 && month == 2) {
            return false;
        }
        if (day == 29 && month == 2) {
            //checking if it is a leap year
            if (!isLeapYear()) {
                return false;
            }
        }
        //valid month
        if (month > 12 || month < 1) {
            return false;
        }
        //valid year
        if (year < 0 || year > 2024) {
            return false;
        }
        return true;
    } //check if the date is a valid calendar date

    public boolean isLeapYear() {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

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


    public static void main(String[] args){

    }
}
