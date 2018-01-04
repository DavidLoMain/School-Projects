/*
Course:        COMP282
Semester:      SUMMER 2017
Assignment:    Project 1: CrazyPoker
File Name:     Card.java
Author:        Lo, David
Student ID:    107378113
email address: david.lo.239@my.csun.edu
*/

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;

public class Card
{
  private char strvalue;
  private char strsuit;

  // Constructor
  // Separates the argument so they can be used later
  // "6s" separates to '6' and 's'
  public Card(String card)
  {
    strvalue = card.charAt(0);
    strsuit  = card.charAt(1);
  }
  
  public String CardSuit()
  {
    
    String s = "This is not a suit";
    
    if(Character.isLetter(strsuit))
    {
      if(strsuit == 's')
        s = "Spade";
      else if(strsuit == 'h')
        s = "Heart";
      else if(strsuit == 'c')
        s = "Club";
      else if(strsuit == 'd')
        s = "Diamond";
    }
    return s;    
  }
  
  // Gave value 14 to A rather than 1
  public int CardValue()
  {
    int intValue;
    switch(strvalue)
    {
      case '2': intValue = 2;
              break;
      case '3': intValue = 3;
              break;
      case '4': intValue = 4;
              break;
      case '5': intValue = 5;
              break;
      case '6': intValue = 6;
              break;
      case '7': intValue = 7;
              break;
      case '8': intValue = 8;
              break;
      case '9': intValue = 9;
              break;
      case 'T': intValue = 10;
              break;
      case 'J': intValue = 11;
              break;
      case 'Q': intValue = 12;
              break;
      case 'K': intValue = 13;
              break;
      case 'A': intValue = 14;
              break;
      default: intValue = 0;        
    }
    return intValue;
  }
}
