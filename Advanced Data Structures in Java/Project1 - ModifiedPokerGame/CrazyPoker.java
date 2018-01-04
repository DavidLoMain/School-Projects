/*
Course:        COMP282
Semester:      SUMMER 2017
Assignment:    Project 1: CrazyPoker
File Name:     CrazyPoker.java
Author:        Lo, David
Student ID:    107378113
email address: david.lo.239@my.csun.edu
*/

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class CrazyPoker
{
  // The try catch statements doesnt have to be in order
  // based on points since I included a 
  // replaceIfGreater(score, temp); to check
  private static int score(Card first,
                           Card second, 
                           Card third, 
                           Card fourth, 
                           Card fifth, 
                           Card sixth)
  {
    int score = 0;

    try
    {
      int temp = DinnerPartyTest(first, second, third, 
                                 fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = PoliticsTest(first, second, third,
                              fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = OrgyTest(first, second, third, 
                          fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = KingdomTest(first, second, third, 
                             fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
 
    try
    {
      int temp = HomosapiensTest(first, second, third, 
                                 fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = OverfullHouseTest(first, second, third, 
                                   fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = FourOfAKindTest(first, second, third, 
                                 fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = MonarchyTest(first, second, third, 
                              fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = EvenOddTest(first, second, third, 
                             fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = MonochromaticTest(first, second, third, 
                                   fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = StraightTest(first, second, third, 
                              fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = FullHouseTest(first, second, third, 
                               fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}    
    try
    {
      int temp = SwingersTest(first, second, third, 
                              fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = FlushTest(first, second, third, 
                           fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = RainbowTest(first, second, third, 
                             fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = numberOfThreeOfAKind(first, second, third, 
                                      fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = numberOfPairs(first, second, third, 
                               fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}
    try
    {
      int temp = StraightFlushTest(first, second, third, 
                                   fourth, fifth, sixth);
      score = replaceIfGreater(score, temp);
    }
    catch(Throwable T){}

    return score;
  }  

  // Replaces the first value with the second if the second if greater
  private static int replaceIfGreater(int first, int second)
  {
    if(second > first)
      first = second;
    return first;
  }

  // Calculates the number of each type of suit in the six cards
  private static int[] numberOfEachSuit(Card first, Card second, 
                                        Card third, Card fourth, 
                                        Card fifth, Card sixth)
  {
    Card[] SixCards = {first, second, third, fourth, fifth, sixth};
    // {Spade, Heart, Club, Diamond}
    int[] TypeOfSuits = new int[4];
    
    for(int index = 0; index<6; index++)
    {
      if(SixCards[index].CardSuit() == "Spade")
        TypeOfSuits[0] = TypeOfSuits[0]+1;
      else if(SixCards[index].CardSuit() == "Heart")
        TypeOfSuits[1] = TypeOfSuits[1]+1;
      else if(SixCards[index].CardSuit() == "Club")
        TypeOfSuits[2] = TypeOfSuits[2]+1;
      else
        TypeOfSuits[3] = TypeOfSuits[3]+1;
    }
    return TypeOfSuits;  
  }

  // Calculates the number of each Card Value
  // Gave King index 0 just so the rest of the numbers
  // are easier to think about. Ex. Number of 5's is index 5
  // [0] = King , [1] = Ace, [2] = 2, ... , [11] = Jack, [12] = Queen
  private static int[] numberOfEachNumber(Card first, Card second,
                                          Card third, Card fourth, 
                                          Card fifth, Card sixth)
  {
    Card[] SixCards = {first, second, third, fourth, fifth, sixth};
    int[] TypeOfNumbers = new int[13];

    for(int index = 0; index<6; index++)
    {
      if(SixCards[index].CardValue() == 13)
        TypeOfNumbers[0]++;
      else if(SixCards[index].CardValue() == 14)
        TypeOfNumbers[1]++;
      else if(SixCards[index].CardValue() == 2)
        TypeOfNumbers[2]++;
      else if(SixCards[index].CardValue() == 3)
        TypeOfNumbers[3]++;
      else if(SixCards[index].CardValue() == 4)
        TypeOfNumbers[4]++;
      else if(SixCards[index].CardValue() == 5)
        TypeOfNumbers[5]++;
      else if(SixCards[index].CardValue() == 6)
        TypeOfNumbers[6]++;
      else if(SixCards[index].CardValue() == 7)
        TypeOfNumbers[7]++;
      else if(SixCards[index].CardValue() == 8)
        TypeOfNumbers[8]++;  
      else if(SixCards[index].CardValue() == 9)
        TypeOfNumbers[9]++;  
      else if(SixCards[index].CardValue() == 10)
        TypeOfNumbers[10]++;    
      else if(SixCards[index].CardValue() == 11)
        TypeOfNumbers[11]++;    
      else if(SixCards[index].CardValue() == 12)
        TypeOfNumbers[12]++;      
    }
    return TypeOfNumbers;
  }

  // Counts Number of Three of a Kinds
  // Three of a kind && Triplet tests done together
  private static int numberOfThreeOfAKind(Card first, Card second,
                                          Card third, Card fourth, 
                                          Card fifth, Card sixth)
  {
    int[] Numbers = numberOfEachNumber(first, second, third,
                                       fourth, fifth, sixth);

    int count = 0;
    for(int index = 0; index<13; index++)
      if(Numbers[index] == 3)
        count++;

    if(count == 2)
      return 17;
    else if(count == 1)
      return 5;
    else
      return 0;
  }

  // This method does the Rainbow and 
  // Non-Rainbow Test Together
  private static int RainbowTest(Card first,
                                 Card second, 
                                 Card third, 
                                 Card fourth, 
                                 Card fifth, 
                                 Card sixth)
  {
    // Space, Hearth, Club, Diamond
    int[] SpadeHeartClubDiamond = numberOfEachSuit(first,
                                                   second, 
                                                   third, 
                                                   fourth, 
                                                   fifth, 
                                                   sixth);
    
    if((SpadeHeartClubDiamond[0] > 0) &&
       (SpadeHeartClubDiamond[1] > 0) &&
       (SpadeHeartClubDiamond[2] > 0) &&
       (SpadeHeartClubDiamond[3] > 0))
      return 3;
    else
      return 1;   
  }
  
  // This method takes in 6 cards
  // and counts the number of Pairs.
  private static int numberOfPairs(Card first, Card second, 
                                   Card third, Card fourth, 
                                   Card fifth, Card sixth)
  {
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    int NumberOfPairs = 0;
    
    for(int index = 0;index<13;index++)
    {
      if(Numbers[index] == 2)
        NumberOfPairs++;
    }

    if(NumberOfPairs == 3)
      return 10;
    if(NumberOfPairs == 2)
      return 4;
    if(NumberOfPairs == 1)
      return 2;

    return 0;
  }

  // Checks if All six cards
  // have the same suit
  private static int FlushTest(Card first, Card second, 
                               Card third, Card fourth, 
                               Card fifth, Card sixth)
  {
    int[] Suits = numberOfEachSuit(first, second, third, 
                                   fourth, fifth, sixth);
    for(int index = 0; index < 4; index++)
    {
      if(Suits[index] == 6)
        return 16;
    }
    return 0;
  }

  private static int SwingersTest(Card first, Card second, 
                                  Card third, Card fourth, 
                                  Card fifth, Card sixth)
  {
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);

    // I chose greater than 1 rather than == 2
    // because Ks Qs 8d Kh Kd Qd is a Swingers
    // hand even though there are more than
    // two Kings.
    if((Numbers[0] > 1) && (Numbers[12] > 1))
    {
      // [Spade, Heart, Club, Diamond]
      int[] KQSuits = new int[4];
      Card[] SixCards = {first, second, third, 
                         fourth, fifth, sixth};
      for(int index = 0; index < 6; index++)
      {
        if((SixCards[index].CardValue() == 13) || 
           (SixCards[index].CardValue() == 12))
        {
          if(SixCards[index].CardSuit() == "Spade")
            KQSuits[0]++;
          else if(SixCards[index].CardSuit() == "Heart")
            KQSuits[1]++;
          else if(SixCards[index].CardSuit() == "Club")
            KQSuits[2]++;
          else
            KQSuits[3]++;
        }
      }
      
      int DoubleSuitCount = 0;
      for(int index2 = 0; index2 < 4; index2++)
      {
        if(KQSuits[index2] > 1)
          DoubleSuitCount++;
      }
      
      if(DoubleSuitCount > 1)
        return 6;
    }
    return 0;
  }

  private static int FullHouseTest(Card first, Card second, 
                                   Card third, Card fourth, 
                                   Card fifth, Card sixth)
  {
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    
    int Pairs = numberOfPairs(first, second, third, 
                              fourth, fifth, sixth);
    int Trips = numberOfThreeOfAKind(first, second, third, 
                                     fourth, fifth, sixth);

    if((Pairs == 2) && (Trips == 5))
      return 9; 
    return 0;
  }

  // The General 5-Card Straight is tested after the Ace 
  // case so that the code will not return a score 
  // of 5-Card Straight when you are testing a hand
  // of A, 2, 3, 4, 5, 6
  private static int StraightTest(Card first, Card second, 
                                  Card third, Card fourth, 
                                  Card fifth, Card sixth)
  {
    Card[] SixCards = {first, second, third, fourth, fifth, sixth};
    int[] SortedCard = new int[6];
    for(int index = 0; index < 6; index++)
    {
      SortedCard[index] = SixCards[index].CardValue();
    }  
    Arrays.sort(SortedCard);
    // 6-Card Straight Test
    if((SortedCard[1] == SortedCard[0]+1) &&
       (SortedCard[2] == SortedCard[1]+1) &&
       (SortedCard[3] == SortedCard[2]+1) &&
       (SortedCard[4] == SortedCard[3]+1) &&
       (SortedCard[5] == SortedCard[4]+1))
       return 13;
       
    // Checking Ace cases
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    if(Numbers[1] >0)
    {
      if((Numbers[2] > 0) &&
         (Numbers[3] > 0) &&
         (Numbers[4] > 0) &&
         (Numbers[5] > 0))
        if(Numbers[6] > 0) 
          return 13;
        else
          return 7;
    }
    // 5-Card General Straight Test W/O Ace
    // Algorithm is based on the score algorithm 
    // provided during lecture.
    for(int one = 0; one < 2; one++)
      for(int two = one + 1; two < 3; two++)
        for(int three = two + 1; three < 4; three++)
          for(int four = three + 1; four < 5; four++)
            for(int five = four + 1; five <6; five++)
            {
              if((SortedCard[two] == SortedCard[one]+1) &&
                 (SortedCard[three] == SortedCard[two]+1) &&
                 (SortedCard[four] == SortedCard[three]+1) &&
                 (SortedCard[five] == SortedCard[four]+1))
                return 7;
            }             
    
    return 0;
  }

  private static int StraightFlushTest(Card first, Card second, 
                                       Card third, Card fourth, 
                                       Card fifth, Card sixth)
  {
    int Straight = StraightTest(first, second, third, 
                                fourth, fifth, sixth);
    int Flush = FlushTest(first, second, third, fourth, fifth, sixth);
    
    if((Straight == 7) && (Flush == 16))
      return 21;
    if((Straight == 13) && (Flush == 16))
      return 22;
    return 0;
  }

  private static int MonochromaticTest(Card first, Card second, 
                                       Card third, Card fourth, 
                                       Card fifth, Card sixth)
  {
    int[] SpadeHeartClubDiamond = numberOfEachSuit(first, second, 
                                                   third, fourth, 
                                                   fifth, sixth);
    
    if((SpadeHeartClubDiamond[0]==0 && SpadeHeartClubDiamond[2]==0) ||
       (SpadeHeartClubDiamond[1]==0 && SpadeHeartClubDiamond[3]==0))
      return 8;
    else
      return 0;
  }
  
  // This method first checks whether you have
  // exactly one of each Face cards, then
  // checks to see if their suits are the same.
  private static int MonarchyTest(Card first, Card second, 
                                  Card third, Card fourth, 
                                  Card fifth, Card sixth)
  {
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    int[] Suits   = numberOfEachSuit(first, second, third, 
                                     fourth, fifth, sixth);
    Card[] SixCards = {first, second, third, 
                       fourth, fifth, sixth};

    if((Numbers[0] == 1)&&(Numbers[11] == 1)&&(Numbers[12] == 1))
    {
      String[] JQKSuitCheck = new String[3];
      for(int index = 0; index < 6; index++)
      {
        if(SixCards[index].CardValue() == 11)
          JQKSuitCheck[0] = SixCards[index].CardSuit();
        if(SixCards[index].CardValue() == 12)
          JQKSuitCheck[1] = SixCards[index].CardSuit();
        if(SixCards[index].CardValue() == 13)
          JQKSuitCheck[2] = SixCards[index].CardSuit();
      }
      if((JQKSuitCheck[0] == "Spade"   && 
          JQKSuitCheck[1] == "Spade"   && 
          JQKSuitCheck[2] == "Spade") ||
         (JQKSuitCheck[0] == "Heart"   && 
          JQKSuitCheck[1] == "Heart"   && 
          JQKSuitCheck[2] == "Heart") ||
         (JQKSuitCheck[0] == "Club"    &&
          JQKSuitCheck[1] == "Club"    && 
          JQKSuitCheck[2] == "Club") ||
         (JQKSuitCheck[0] == "Diamond" &&
          JQKSuitCheck[1] == "Diamond" && 
          JQKSuitCheck[2] == "Diamond"))
        return 11;
    }
    return 0;   
  }
  
  private static int EvenOddTest(Card first, Card second, 
                                 Card third, Card fourth, 
                                 Card fifth, Card sixth)
  {
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    
    if(Numbers[2] + Numbers[4] + Numbers[6] 
                  + Numbers[8] + Numbers[10] == 6)
      return 12;
    if(Numbers[3] + Numbers[5] + Numbers[7] + Numbers[9] == 6)
      return 15; 
      
    return 0;    
  }
  
  private static int FourOfAKindTest(Card first, Card second, 
                                     Card third, Card fourth, 
                                     Card fifth, Card sixth)
  {
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    
    for(int index = 0; index<13; index++)
    {
      if(Numbers[index] == 4)
        return 14;
    }
    return 0;
  }

  private static int OverfullHouseTest(Card first, Card second, 
                                       Card third, Card fourth, 
                                       Card fifth, Card sixth)
  {    
    int temp1 = FourOfAKindTest(first, second, third, 
                                fourth, fifth, sixth);
    int temp2 = numberOfPairs(first, second, third, 
                              fourth, fifth, sixth);
    if((temp1 == 14) && temp2 == 2)
      return 18;
         
    return 0;
  }

  private static int HomosapiensTest(Card first, Card second, 
                                     Card third, Card fourth, 
                                     Card fifth, Card sixth)
  {
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);

    if((Numbers[0] + Numbers[11] + Numbers[12]) == 6)
      return 19;
    else
      return 0;
  }

  private static int KingdomTest(Card first, Card second, 
                                 Card third, Card fourth, 
                                 Card fifth, Card sixth)
  {
    int[] Suits = numberOfEachSuit(first, second, third, 
                                   fourth, fifth, sixth);
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    
    if((Numbers[0] == 1) && 
       (Numbers[11] == 1) && 
       (Numbers[12] == 1) &&
       (Suits[0] == 6 || Suits[1] == 6 ||
        Suits[2] == 6 || Suits[3] == 6))
      return 20;

    return 0;
  }
  
  private static int OrgyTest(Card first, Card second, Card third, 
                              Card fourth, Card fifth, Card sixth)
  {
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    
    if(Numbers[11] + Numbers[12] == 6)
      return 23;
      
    return 0;
  }

  private static int PoliticsTest(Card first, Card second, Card third, 
                                  Card fourth, Card fifth, Card sixth)
  {
    int[] Suits = numberOfEachSuit(first, second, third, 
                                   fourth, fifth, sixth);
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    
    if((Numbers[0] == 2) && (Numbers[11] == 2) && (Numbers[12] == 2))
    {
      int NonExistentSuits = 0;
      for(int index = 0; index<4; index++)
      {
        if(Suits[index] == 0)
          NonExistentSuits++;
      }
      
      if(NonExistentSuits == 2)
        return 24;
    }
    
    return 0;
  }

  private static int DinnerPartyTest(Card first, Card second, 
                                     Card third, Card fourth, 
                                     Card fifth, Card sixth)
  {
    int[] Suits = numberOfEachSuit(first, second, third, 
                                   fourth, fifth, sixth);
    int[] Numbers = numberOfEachNumber(first, second, third, 
                                       fourth, fifth, sixth);
    
    if((Suits[0] == 0 || Suits[1] == 0 || 
        Suits[2] == 0 || Suits[3] == 0) &&
       (Numbers[0] == 3) && (Numbers[12] == 3))
      return 25;
    else
      return 0; 
  }

  public static void main(String[] args)
  {
    Card firCom = new Card(args[0]);
    Card secCom = new Card(args[1]);
    Card thiCom = new Card(args[2]);
    Card fouCom = new Card(args[3]);
    Card fifCom = new Card(args[4]);      
  
    int NumberofPlayers = (args.length - 5)/3;
   
    int TotalPlayerCards = NumberofPlayers*3;
    int count =3;
    Card[][] AllPlayerCards = new Card[count][];
   
    for(int index1 = 0; index1 < count; index1++)
      AllPlayerCards[index1] = new Card[NumberofPlayers];

    int CurrentArg = 4; 

    for(int PlayerNum = 0; PlayerNum < NumberofPlayers; PlayerNum++)
    {
      for(int CardCount = 0; CardCount < 3; CardCount++)   
      {
        CurrentArg++;
        AllPlayerCards[CardCount][PlayerNum] = new Card(args[CurrentArg]);
      }
    }  

    // Java initializes MaxScores to an Array of all zero
    int[] MaxScores = new int[NumberofPlayers];
    int[] CurrentScores = new int[NumberofPlayers];
    for(int CurrPlayer = 0; CurrPlayer < NumberofPlayers; CurrPlayer++)
      for(int first = 0; first < 3; first++)
        for(int second = first+1; second < 4; second++)
          for(int third = second+1; third < 5; third++)
            for(int fourth = third+1; fourth < 6; fourth++)
              for(int fifth = fourth+1; fifth < 7; fifth++)
                for(int sixth = fifth+1; sixth < 8; sixth++)
                {
                  Card[] EightCardP1 = {firCom,
                                        secCom, 
                                        thiCom, 
                                        fouCom, 
                                        fifCom,
                                        AllPlayerCards[0][CurrPlayer],
                                        AllPlayerCards[1][CurrPlayer],
                                        AllPlayerCards[2][CurrPlayer]
                                       };
                  // Player 1
                  CurrentScores[CurrPlayer] = score(EightCardP1[first],
                                                    EightCardP1[second],
                                                    EightCardP1[third], 
                                                    EightCardP1[fourth],
                                                    EightCardP1[fifth],
                                                    EightCardP1[sixth]);
                  MaxScores[CurrPlayer] = 
                    replaceIfGreater(MaxScores[CurrPlayer],
                                     CurrentScores[CurrPlayer]);
                }  

     // int[] MaxScoresSorted = new int[NumberofPlayers];
      int[] MaxScoresSorted = 
              Arrays.copyOf(MaxScores, MaxScores.length);    
      Arrays.sort(MaxScoresSorted);

      // Below code is to print out the output the Project requires.
      // The array of the unsorted scores: 
      //   {Player 1, Player 2,..., Player N} 
      // The for loop below compares the index of the
      //   sorted Array with the Unsorted Array
      // ToPrint is index4 + 1 because index 0
      //   corresponds with Player 1 etc.
      // AlreadyCounted Array is to resolve any duplicate scores.
      // If two or more Players have the same score, 
      //   the lower Player Number will be displayed first.
      int WinOrder = 1;
      int[] AlreadyCounted = new int[NumberofPlayers];
      
      for(int index3 = (NumberofPlayers -1); index3 > -1; index3--)
      {
        System.out.print(WinOrder + ": ");
        for(int index4 = 0; index4 < NumberofPlayers; index4++)
        {
          if((MaxScoresSorted[index3] == MaxScores[index4]) && 
             (AlreadyCounted[index4] == 0))
          {
            int ToPrint = index4+1;
            System.out.println("Player " + (ToPrint) + MaxScores[index4]);
            AlreadyCounted[index4]++;
            break;
          }
        }     
        WinOrder++;
      }
  }
}