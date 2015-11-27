package com.example.izmir.passwordstrengthmeter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Izmir on 2015-11-18.
 *
 *
 *Class that controls how the algorithm for the PasswordStrengthMeter works.
 The idea is that you can modify or add new methods to customize the PasswordStrengthMeter
 to your needs. The class will return a value 0-5 where 5 is the highest password strength. This is done
 in the getPoints method.
 */

public class PassAlg {
    private int minLength;



    //Default constructor
    public PassAlg(){
        minLength=8;
    }


    //Constructor which will get minimum password length, less than 4 is not allowed because of security concerns
    public PassAlg(int Length){

        if(Length<4){
            minLength=4;
        }else {
            minLength = Length;
        }
    }

    //Public function to set minimum password length, lowest password length is 4 because of security concerns
    public void setMinPasswordLength(int Length){

        if(Length<4){
            minLength=4;
        }else {
            minLength = Length;
        }

    }

    //Function that checks if the minimum length was met
    public boolean meetsMinimumLength(String s){
        boolean result = false;

        if(s.length() >= minLength){
            result=true;
        }
        return result;
    }


    //Function that checks is password contains an uppercase letter
    public boolean hasUpper(String s){

        for(int i=0; i< s.length(); i++){
            if(Character.isUpperCase(s.charAt(i))){
                return true;
            }
        }
        return false;
    }


    //Function that checks is password contains a lowercase letter
    public boolean hasLower(String s){

        for(int i=0; i< s.length(); i++){
            if(Character.isLowerCase(s.charAt(i))){
                return true;
            }
        }
        return false;
    }

    //Function that checks is password contains a number
    public boolean hasNumber(String s){

        for(int i=0; i< s.length(); i++){
            if(Character.isDigit(s.charAt(i))){
                return true;
            }
        }
        return false;
    }

    //Checks is password contains a special character
    public boolean containSpecial(String s){
        boolean r = false;
        Pattern pattern = Pattern.compile("([!#€%&/()=?)])");
        Matcher m = pattern.matcher(s);

        if(m.find()){
            r = true;
        }
        System.out.println(r);

        return r;

    }
//Function that adds all the points together, the higher point means stronger password
    public int getPoints(String password){
        int currentPoints = 0;


        if(hasLower(password)){
            currentPoints++;
        }

        if(hasUpper(password)){
            currentPoints++;
        }

        if(hasNumber(password)){
            currentPoints++;
        }

        if(meetsMinimumLength(password)){
            currentPoints++;
        }

        if(containSpecial(password)){
            currentPoints++;
        }
    return currentPoints;
    }
}
