/**
 * This class represents a Rent object.
 * @version (2022)
 * @author Ariel Arie.
 */
public class Rent
{
    String _name; // Name of the costomer
    Car _car; // Car object
    Date _pickDate; // The pick date
    Date _returnDate;// The return date

    /**
     * Creates a new Rent object
     * The return date must be at least one day after the pickup date, otherwise set it to one day after the pick up date.
     * @param name - the client's name
     * @param car - the rented car
     * @param pick - the pickup date
     * @param ret - the return date
     */
    public Rent (String name, Car car, Date pick, Date ret)
    {
        _name = name;
        _car = new Car(car);
        _pickDate = new Date (pick);
        if (ret.after(_pickDate)) // Checking if the return date of the car is after the date of receipt of the car
        {
            _returnDate = new Date (ret); 
        }

        else _returnDate = pick.tomorrow();

    } // end of Rent method
    /**
     * Copy constructor.
     */
    public Rent (Rent other)
    {
        _name = other._name;
        _car = new Car(other._car);
        _pickDate = new Date (other._pickDate);
        _returnDate = new Date (other._returnDate);
    } // end of Rent method
    /**
     * Gets the car.
     * @return the car.
     */
    public Car getCar()
    {
        return new Car(_car); 
    } // end of getCar method
    /**
     * Gets the name.
     * @return the name.
     */
    public String getName()
    {
        return _name;
    } // end of getName method
    /**
     * Gets the pick up date.
     * @return the pick up date.
     */
    public Date getPickDate()
    {
        return new Date(_pickDate);
    } // end of getPickDate method
    /**
     * Gets the return date.
     * @return the return date.
     */
    public Date getReturnDate()
    {
        return new Date(_returnDate);
    } // end of getReturnDate method
    /**
     * Sets the rented car.
     * @param car - the rented car.
     */
    public void setCar(Car car)
    {
        _car = new Car (car);
    }  // end of setCar method
    /**
     * Sets the client name.
     * @param name - the client name.
     */
    public void setName(String name)
    {
        _name = name;
    }  // end of setName method
    /**
     * Sets the pick up date
     * The pick up date must be at least one day before the return date, otherwise - don't change the pick up date
     * @param pickDate - the pick up date.
     */
    public void setPickDate(Date pickDate)
    {
        if (pickDate.before(_returnDate)) //Checking if the return date of the car is after the new receipt date of the car
        {
            _pickDate = new Date (pickDate);
        }
    }  // end of setPickDate method
    /**
     * Sets the return date
     * The return date must be at least one day after the pick up date, otherwise - don't change the return date
     * @param returnDate - the return date
     */
    public void setReturnDate(Date returnDate)
    {
        if (returnDate.after(_pickDate)) // Checking if the new return date of the car is after the date of receipt of the car
        {
            _returnDate = new Date (returnDate);
        }

    }  // end of setReturnDate method
    /**
     * Check if 2 rents are the same
     * Two rentals are considered the same if their customer name, pickup and return dates, and cars are the same
     * @param other - the rent to compare this rent to
     * @return true if the rents are the same
     */
    public boolean equals(Rent other)
    {
        return ((_name.equals(other._name)) && (_pickDate.equals(other._pickDate)) && (_returnDate.equals(other._returnDate)) && (_car.equals(other._returnDate)));
    }  // end of equals method
    /**
     * Returns the number of rent days
     * @return the number of rent days
     */
    public int howManyDays()
    {
        int _howManyDays = _pickDate.difference(_returnDate); // Calculation of the difference between the day of receipt of the vehicle and the day of delivery
        return _howManyDays;
    }  // end of howManyDays method

    private int dayPrice()
    {
        int day_price = 0;
        if (_car.getType() == 'A')
        {
            day_price = 100;
        }
        else if (_car.getType() == 'B')
        {
            day_price = 150;
        }
        else if (_car.getType() == 'C')
        {
            day_price = 180;
        }
        else if (_car.getType() == 'D')
        {
            day_price = 240;
        }

        return day_price;

    }// end of dayPrice method
    /**
     * Returns the rent total price
     * @return the rent total price
     */
    public int getPrice()
    {
        final int DAYS_IN_WEEK = 7; 
        final double DISCOUNT = 0.9;
        int totalOfDays = this.howManyDays(); // Calculation of the total rental days
        int weeks = (totalOfDays/DAYS_IN_WEEK); // Calculation of the number of full weeks
        int days = (totalOfDays%DAYS_IN_WEEK); // Calculating the few days left

        double price = ((days*this.dayPrice()) + (DISCOUNT*((weeks*DAYS_IN_WEEK)*this.dayPrice()))); // Calculating the discount for the days of the full weeks and adding the price of the individual days

        return (int)price;
    }  // end of getPrice method
    /**
     * Try to upgrade the car to a better car
     * If the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost, otherwise - don't upgrade
     * @param car - the car to upgrade to
     * @return the upgrade cost
     */
    public int upgrade (Car newCar)
    {
        int extraCharge = 0;
        if (newCar.better(this._car)) // Testing if the new car is better than the current one
        {
            int priceBeforeUpgrade = this.getPrice(); // First price calculation
            this.setCar(newCar);
            int priceAfterUpgrade = this.getPrice();// New price calculation
            extraCharge = priceAfterUpgrade - priceBeforeUpgrade; // Calculate the difference

        }
        return extraCharge;
    } // end of upgrade method
    /**
     * Check if there is a double listing of a rent for the same person and car with an overlap in the rental days
     * If there is - return a new rent object with the unified dates, otherwise - return null.
     * @param other - the other rent
     * @return the unified rent or null
     */
    public Rent overlap (Rent other)
    {
        if (_name.equals(other._name) && _car.equals(other._car))
        {
            if ((other._pickDate.after(_pickDate) && other._pickDate.before(_returnDate) && other._returnDate.after(_returnDate)) || (_returnDate.equals(other._pickDate))) //A case where the lease received starts in the middle of the lease period of the current object and ends afterwards or in case where the lease received begins at the end of the current lease
            {
                return new Rent(_name, _car, _pickDate, other._returnDate);
            }

            else if ((_pickDate.after(other._pickDate) && _pickDate.before(other._returnDate) && _returnDate.after(other._returnDate)) || (other._returnDate.equals(_pickDate))) // A case where the received lease starts before the lease period of the current object and ends in the middle or in case where the lease received begins before the lease period of the current object and ends at the beginning of the current lease
            {
                return new Rent(_name, _car, other._pickDate, _returnDate); 
            }

            else if (_returnDate.before(other._pickDate) || (other._returnDate.before(other._pickDate))) // A case where there is no overlap between the rental periods
            {
                return null; 
            }

            else if (((other._pickDate.after(_pickDate)) || (_pickDate.equals(other._pickDate)) && other._returnDate.before(_returnDate)) || ((_pickDate.equals(other._pickDate) || (this._pickDate.before(other._pickDate)) && _returnDate.equals(other._returnDate)))) // A case where the rent received is contained within or equal to the current rent
            {                
                return new Rent(this);
            }

            else if (((_pickDate.after(other._pickDate)) || (_pickDate.equals(other._pickDate)) && _returnDate.before(other._returnDate)) || (_pickDate.after(other._pickDate) && _returnDate.equals(other._returnDate))) // A case where the lease received includes the current lease
            {
                return new Rent(other);
            }
            else return null;
        }
        else return null;
    } // end of overlap method

    /**
     * Returns a String that represents this rent
     * @return String that represents this rent in the following format: Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString()
    {
        return ("Name:" +_name+ " From:" +_pickDate+ " To:" +_returnDate+ " Type:" +this._car.getType() +  " Days:" +this.howManyDays()+ " Price:" +this.getPrice());
    } // end of toString method
} // end of class Rent

