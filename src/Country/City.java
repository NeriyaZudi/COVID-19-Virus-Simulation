/*
  Authors: Neriya Zudi (ID:207073545)
  Email:  neriyazudi@Gmail.com
  Department of Computer Engineering - Assignment 1 - Advanced Object-Oriented Programming
*/
package Country;


import Location.Location;
import Population.Person;

import java.util.List;

public class City extends Settlement {

    public City(String name, Location location, List<Person> population, RamzorColor ramzorColor)
    {
        super(name,location,population,ramzorColor);
    }

    @Override
    public RamzorColor calculateRamzorGrade() {
        double p = contagiousPercent();//Calculate contagious percentage
        double new_c=0.2*(Math.pow(4,1.25*p));//Calculate a new ramzor grade
        this.setRamzorGrade(new_c);//Update a new ramzor grade
        //Returns a new ramzor color
        if(new_c <= 0.4)
            return RamzorColor.Green;
        else if(new_c <= 0.6)
            return RamzorColor.Yellow;
        else if(new_c <= 0.8)
            return RamzorColor.Orange;
        else
            return RamzorColor.Red;
    }

    @Override
    public String toString() {
        return "## City Settlement ## " + super.toString();
    }

    /*public boolean setRamzorGrade(double newGrade) {
        return super.setRamzorGrade(newGrade);
    }*/
}
