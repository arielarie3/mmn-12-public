
/**
 * This class represents a car for a car rental agency
 * @author Ariel Arie
 */

public class Car {

    private int _id; // 7 digit number
    private char _type; // A,B,C,D
    private String _brand; // the vehicle manufacturer
    private boolean _isManual; // gearbox type 

    //The class constants:
    private final int MAX_ID_NUM = 9999999; // Upper range of ID number
    private final int MIN_ID_NUM = 999999; // Lower range of ID number
    private final int DEF_ID = 9999999; // Default ID number
    private final char DEF_TYPE = 'A'; // Default type

    /**
     * Constructs a car. Initialize an instance of Car With the car details
     * @param lenght the length of car ID (_id)
     * @param _id the car identification number
     * @param _type the car rating
     * @param _brand the car brand 
     * @param _isManual the gearbox type
     */

    public Car (int id, char type, String brand, boolean isManual){

        if(id > MIN_ID_NUM && id <= MAX_ID_NUM) // Checking the validity of the ID
            _id = id;
        else _id = DEF_ID;

        if(type == 'A' || type == 'B' || type == 'C' || type == 'D') // Checking the rating of the car
            _type = type;
        else _type = DEF_TYPE;

        _brand = brand;
        _isManual = isManual; 

    } // end of Car method

    /**
     * Copy Constructor
     * Initialize an instance of Car identical to the given Car
     * @param other The Car to copy
     * @param _id the car identification number
     * @param _type the car rating
     * @param _brand the car brand 
     * @param _isManual the gearbox type
     */

    public Car(Car other){

        _id = other._id;
        _type = other._type;
        _brand = other._brand;
        _isManual = other._isManual;

    } // end of Car method

    /**
     * Returns the car ID
     * @return car ID
     */

    public int getId(){
        return _id;
    }  // end of getId method

    /**
     * Returns the car type
     * @return car type
     */

    public char getType(){
        return _type;
    } // end of getType method

    /**
     * Returns the car brand
     * @return car brand
     */

    public String getBrand(){
        return _brand;
    }  // end of getBrand method

    /**
     * Returns if the car is manual or not
     * @return car gearbox type
     * @return true if the car is manual, otherwise false
     */

    public boolean isManual(){
        return _isManual;
    } // end of isManual method

    /**
     * Sets the ID of given Car
     * If the ID is an Invalid, the ID car will remain as is
     * @param _id the car ID 
     * @param lenght the length of car ID (_id)
     */

    public void setId(int id){

        if(id > MIN_ID_NUM && id <= MAX_ID_NUM) // Checking the validity of the ID
            _id = id;

    } // end of setId method

    /**
     * Sets the type of given Car
     * If the type is an Invalid, the type car will remain as is
     * @param _type the car rating
     */
    public void setType(char type){
        if(type == 'A' || type == 'B' || type == 'C' || type == 'D') // Checking the rating of the car
            _type = type;

    } // end of setType method

    /**
     * Sets the brand of given Car
     * @param _brand the car brand
     */   

    public void setBrand(String brand){

        _brand = brand;
    } // end of setBrand method

    /**
     * Sets the gearbox type of given Car
     * @param _isManual the gearbox type
     */ 
    public void setIsManual(boolean isManual){

        _isManual = isManual;        
    } // end of setIsManual method

    /**
     * Returns a string representation of the Car object
     */ 

    public String toString(){
        if(_isManual)
            return ("id:"+_id+ " type:"+_type+ " brand:"+_brand+ " gear:manual"); 

        else return ("id:"+_id+ " type:"+_type+ " brand:"+_brand+ " gear:auto");

    } // end of toString method

    /**
     * This method check if two cars are the same . 
     * Two cars are considered identical if they have the same rating, the same manufacturer and the same transmission.
     * @param other the car to compare to
     * @return true if the cars are the same, otherwise false
     */

    public boolean equals(Car other){
        return _type == other._type && _brand.equals(other._brand) && _isManual == other._isManual;  
    } // end of equals method

    /**
     * This method checks if the car is better than another car
     * A car will be considered better than another car if its rating is higher. If both have the same rating then an automatic car will be considered better than the manual car
     * @param other the car to compare to
     * @return true if the car is better, otherwise false
     */

    public boolean better(Car other){

        if (_type > other._type) // Checking if the vehicle type is better
        { 
            return true; 
        }
        else if (_type == other._type)  
        {

            if (_isManual == false && other._isManual== true) // Comparison of the gearboxes of the cars
            {
                return true;
            }
        }
        return false;

    } // end of better method

    /**
     * This method checks if the car is worse than another car
     * A car will be considered worse than another car if its rating is lower. If both have the same rating then an manual car will be considered worse than the automatic car
     * @param other the car to compare to
     * @return true if the car is better, otherwise false
     */

    public boolean worse(Car other){

        return other.better(this);

    } // end of worse method
}// end of class Car

