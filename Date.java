public class Date implements Comparable<Date>, Cloneable {

    // private instance fields
    private int month;
    private int day;
    private int year;

    // default constructor
    public Date() {
        this.month = 1;
        this.day = 1;
        this.year = 1970;
    }

    // non-default constructor
    public Date(int month, int day, int year) {
        if (month < 1 || month > 12) {
            new Date();
        }
        else if (day < 1 || day > 31) {
            new Date();
        }
        else if (year < 1970) {
            new Date();
        }
        else {
            this.month = month;
            this.day = day;
            this.year = year;
        }
    }

    // accessor methods
    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    //mutator methods
    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // returns a String in the form mm/dd/yyyy
    public String toString() {
        String monthString;
        String dayString;
        String yearString = String.valueOf(this.year);
        if (month < 10) {
            monthString = "0" + String.valueOf(month);
        }
        else {
            monthString = String.valueOf(month);
        }
        if (day < 10) {
            dayString = "0" + String.valueOf(day);
        }
        else {
            dayString = String.valueOf(day);
        }
        return monthString + "/" + dayString + "/" + yearString;
    }

    // checks if two Date objects have the same month, day, and year
    public boolean equals(Date o) {
        return this.month == o.month &&
                this.day == o.day && this.year == o.year;
    }

    // clones an object and returns a Date object
    public Date clone() throws CloneNotSupportedException {
        return (Date) super.clone();
    }


    // compares two date objects
    public int compareTo(Date o) {
        if (this.equals(o)) {
            return 0;
        }
        if (o.year > this.year) {
            return 1;
        }
        else if (o.year < this.year) {
            return -1;
        }
        else {
            if (o.month > this.month) {
                return 1;
            }
            else if (o.month < this.month) {
                return -1;
            }
            else {
                if (o.day > this.day) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        }
    }
}
