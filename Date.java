/**
 * This class represents a date.
 * @version (2022)
 * @author Ariel Arie.
 */
public class Date {

    private int _day; // Number between 1 - 31
    private int _month; // Number between 1 - 12
    private int _year; //  4 digit number

    //The class constants:

    private final int MAX_DAY = 31; // Upper range of days in a month
    private final int MIN_DAY = 1; // Lower range of days in a month
    private final int MAX_MONTH = 12; // Upper range of the months of the year
    private final int MIN_MONTH = 1; // Lower range of the months of the year
    private final int MAX_YEAR = 9999; // Upper range of the years
    private final int MIN_YEAR = 1000; // Lower range of the years

    /**
     * A private method that checks whether a year is a leap year or not. 
     * This method will be used for the isValidDate method:
     * @param this the year to chek
     */

    private boolean isLeapYear(int year)
    {
        if (year % 400 == 0)
        {
            return true;
        }

        else if ((year % 4 == 0) && ((year % 100) != 0)) // Check that the year is divisible by 4 but not by 100
        {
            return true;
        }

        else return false;

    } // end of isLeapYear method

    /**
     * A private method that gets three integers representing day,month and year and checks whether the date is valid.
     * This method will be used in the future to check the correctness of the date for any method in a simple and easy way
     * @param day the day of the date
     * @param month the mounth of the date
     * @param year the year of the date
     */

    private boolean isValidDate(int day, int month, int year)
    {
        if (((day >= MIN_DAY) && (day <= MAX_DAY) && (month >= MIN_MONTH) && (month <= MAX_MONTH) && (year >= MIN_YEAR) && (year <= MAX_YEAR))) // Checking whether the checked date is within the correct ranges
        {

            if (month == 1|| month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) 
            {
                return true;
            }

            else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30)
            {
                return true;
            }

            else if (month == 2)
            {
                if (isLeapYear(year) && day <= 29)
                {
                    return true;
                }

                else if (isLeapYear(year) == false && day <= 28)
                {
                    return true;
                }

            }

        }
        return false;
    } // end of isValidDate method

    /**
     * Constructs a Date.
     * @param day the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year the year (4 digits) 
     */
    public Date(int day, int month, int year)
    {

        boolean chek = isValidDate(day,month,year); // Checking that the received data forms a valid date
        if (chek)
        {
            _day = day;
            _month = month;
            _year = year;
        }
        else 
        {
            _day = 1;
            _month = 1;
            _year = 2000;
        }
    } // end of Date method

    /**
     * Copy constructor.
     */
    public Date (Date other)
    {
        _day = other._day;
        _month = other._month;
        _year = other._year;  
    } // end of Date method

    /**
     * Gets the day.
     * @return the day.
     */
    public int getDay()
    {
        return _day; 
    } // end of getDay method

    /**
     * Gets the month.
     * @return the month.
     */
    public int getMonth()
    {
        return _month;
    } // end of getMonth method

    /**
     * Gets the year.
     * @return the year.
     */
    public int getYear()
    {
        return _year;
    } // end of getYear method

    /**
     * Set the day (only if date remains valid).
     * @param dayToSet the day value to be set.
     */
    public void setDay(int dayToSet)
    {

        if (isValidDate(dayToSet,_month,_year)) // Checking that the data received creates a valid date
        {
            _day = dayToSet;
        }
    }  // end of setDay method

    /**
     * Set the month (only if date remains valid).
     * @param monthToSet the month value to be set.
     */
    public void setMonth(int monthToSet)
    {
        if (isValidDate(_day,monthToSet,_year)) // Checking that the data received creates a valid date
        {
            _month = monthToSet;
        } 
    } // end of setYear method

    /**
     * Set the year (only if date remains valid).
     * @param yearToSet the year value to be set.
     */
    public void setYear(int yearToSet)
    {
        if (isValidDate(_day,_month,yearToSet)) // Checking that the data received creates a valid date
        {
            _year = yearToSet;
        }

    } // end of setYear method

    /**
     * Check if 2 dates are the same.
     * @param other the date to compare this date to
     * return true if the dates are the same, otherwise false
     */
    public boolean equals (Date other)
    {
        return _day == other._day && _month == other._month && _year == other._year; 
    } // end of equals method

    /**
     * Check if this date is before other date.
     * @param other date to compare this date to.
     * return true if this date is before other date, otherwise false.
     */
    public boolean before (Date other)
    {
        if (_year < other._year) // Comparison between the years
        {
            return true; 
        }
        else if (_month < other._month && _year == other._year) // If the years are the same we will compare the months
        {
            return true;  
        }

        else if (_day < other._day && _year == other._year && _month == other._month) // If the years and months are the same we will compare the days
        {
            return true;  
        }

        else return false;       
    } // end of before method

    /**
     * Check if this date is after other date.
     * @param other date to compare this date to.
     * return true if this date is after other date, otherwise false.
     */
    public boolean after (Date other)
    {
        return (other.before(this));     
    } // end of after method

    /**
     * A private method that computes the day number since the beginning of the Christian counting of years 
     * @param day the day of the date
     * @param month the mounth of the date
     * @param year the year of the date
     * @return day number since the beginning of the Christian counting of years
     */
    private int calculateDate ( int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    } // end of calculateDate method

    /**
     * Calculates the difference in days between two dates
     * @param other the date to calculate the difference between
     * return the number of days between the dates (non negative value)
     */
    public int difference (Date other)
    {
        int difference = Math.abs(calculateDate(this._day, this._month, this._year) - calculateDate(other._day, other._month, other._year));
        return difference; 
    } // end of difference method

    /**
     * Returns a String that represents this date
     * return String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString()
    {
        if (_day < 10 && _month>9)
            return ("0"+_day+"/"+_month+"/"+_year);
        else if (_day > 9 && _month<10)
            return (_day+"/0"+_month+"/"+_year);
        else if (_day < 10 && _month<10)
            return ("0"+_day+"/0"+_month+"/"+_year);
        else return (+_day+"/"+_month+"/"+_year);
    } // end of toString method

    /**
     * Calculate the date of tomorrow
     * return the date of tomorrow
     */
    public Date tomorrow()
    {
        int tomorrowDay = _day;
        int tomorrowMonth = _month;
        int tomorrowYear = _year;

        if ((tomorrowMonth == 1|| tomorrowMonth == 3 || tomorrowMonth == 5 || tomorrowMonth == 7 || tomorrowMonth == 8 || tomorrowMonth == 10) && (tomorrowDay == 31)) // A case where the resulting day is the last day of the months with 31 days 
        {
            tomorrowDay = 1;
            tomorrowMonth ++;
        }

        else if ((tomorrowMonth == 4 || tomorrowMonth == 6 || tomorrowMonth == 9 || tomorrowMonth == 11) && (tomorrowDay == 30)) // A case where the resulting day is the last day of the 30-day months
        {
            tomorrowDay = 1;
            tomorrowMonth ++;
        }

        else if ((tomorrowMonth == 12) && (tomorrowDay == 31)) // A case where the resulting day is the last day of the year
        {
            tomorrowDay = 1;
            tomorrowMonth = 1;
            tomorrowYear ++;
        }

        else if (tomorrowMonth == 2)
        {
            if (isLeapYear(tomorrowYear) && (tomorrowDay == 29)) // End of a month of a leap year
            {
                tomorrowDay = 1;
                tomorrowMonth = 3;
            }

            else if (isLeapYear(tomorrowYear) == false && (tomorrowDay == 28)) // End of a month of a non-leap year
            {
                tomorrowDay = 1;
                tomorrowMonth = 3;
            }
            else tomorrowDay ++;

        }
        else tomorrowDay ++;
        Date tomorrow = new Date(tomorrowDay,tomorrowMonth,tomorrowYear);
        return tomorrow;

    } // end of tomorrow method

}
