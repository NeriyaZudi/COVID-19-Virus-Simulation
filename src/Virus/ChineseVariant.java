/*
  Authors: Neriya Zudi (ID:207073545)
  Email:  neriyazudi@Gmail.com
  Department of Computer Engineering - Assignment 1 - Advanced Object-Oriented Programming
*/
package Virus;

import Population.Person;
import Population.Sick;
import Simulation.Clock;

import java.util.ArrayList;


public class ChineseVariant implements IVirus {

    public ChineseVariant() {

    }

    public static String getName() {
        return "Chinese Variant";
    }

    //Interface Realization
    @Override
    public double contagionProbability(Person p) {
        if (p.getAge() >= 0 && p.getAge() <= 18)
            return 0.2 * p.contagionProbability();
        else if (p.getAge() >= 19 && p.getAge() <= 55)
            return 0.5 * p.contagionProbability();
        else
            return 0.7 * p.contagionProbability();
    }

    @Override
    public boolean tryToContagion(Person p1, Person p2) {
        Sick person = new Sick((Sick) p1);
        if ((Clock.now()-person.getContagiousTime()) < 5) {
            return false;
        }
        double probability = 0;//Probability of infection
        Random r = new Random();
        if (p2 instanceof Sick) {//Unable to infect a sick person
            System.out.println("alredy sick");
            return false;
        }

        double d = p1.getDistance(p2.getLocation());//Calculate distance between two people
        probability = p2.contagionProbability() * Math.min(1, (0.14) * Math.exp(2 - (0.25 * d)));//Calculate the probability of infection

        //System.out.println("probal "+probability);
        return probability >= r.getRandomNumber();//Returns true or false if contagion succeeds


    }

    @Override
    public boolean tryToKill(Sick s) {
        double probability = 0;
        double t = Clock.now() - s.getContagiousTime();
        Random r = new Random();
        if (s.getAge() >= 0 && s.getAge() <= 18)
            probability = Math.max(0, 0.001 - (0.01 * 0.001) * Math.pow((t - 15), 2));
        else if (s.getAge() >= 19 && s.getAge() <= 55)
            probability = Math.max(0, 0.05 - (0.01 * 0.05) * Math.pow((t - 15), 2));
        else
            probability = Math.max(0, 0.1 - (0.01 * 0.1) * Math.pow((t - 15), 2));

        return probability >= r.getRandomNumber();
    }


    @Override
    public boolean isEqual(IVirus virus) {
        return (this.toString().equals(virus.toString()));
    }

    @Override
    public String toString() {
        return "Chinese Variant";
    }
}
