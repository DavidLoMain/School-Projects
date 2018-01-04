
/**
 * Course: COMP282
 * Semester: SUMMER 2017
 * Assignment: Project 3: Dijkstra's Algorithm
 * File Name: Graph.java
 * Author: Lo, David
 * Student ID: 107378113
 * Email Address: david.lo.239@my.csun.edu
 */
 
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Arrays;
 
class Graph
{
  private String path;
  private int      NumberOfNodes;
  private char[]   ActiveNodes;
  private String[] Connections;
  private int[]    Weights;
 
  /**
   * Constructor for Graph
   *
   */
  public Graph(String args)
  {
    path = args;
    NumberOfNodes = 0;
    ActiveNodes = new char[2];
  }
  
  /**
   *
   *  Returns array of char
   */
  public int[] checksTypesOfNodes(String[] array)
  {
    // Using this array to count how many nodes 
    // are in the text file
    // I have a bunch of IFs instead of If-else so that the
    // second character will be checked as well
    int[] Nodes = new int[26];
    for(int index = 0; index < array.length;index++)
    {
      if((array[index].charAt(0) == 'a') ||
         (array[index].charAt(1) == 'a'))
        Nodes[0]++;
      if(array[index].charAt(0) == 'b' || 
        (array[index].charAt(1) == 'b'))
        Nodes[1]++;
      if(array[index].charAt(0) == 'c' ||
         array[index].charAt(1) == 'c')
        Nodes[2]++;
      if(array[index].charAt(0) == 'd' ||
         array[index].charAt(1) == 'd')
        Nodes[3]++;
      if(array[index].charAt(0) == 'e' || 
         array[index].charAt(1) == 'e')
        Nodes[4]++;
      if(array[index].charAt(0) == 'f' || 
         array[index].charAt(1) == 'f')
        Nodes[5]++;
      if(array[index].charAt(0) == 'g' || 
         array[index].charAt(1) == 'g')
        Nodes[6]++;
      if(array[index].charAt(0) == 'h' || 
         array[index].charAt(1) == 'h')
        Nodes[7]++;
      if(array[index].charAt(0) == 'i' || 
         array[index].charAt(1) == 'i')
        Nodes[8]++;
      if(array[index].charAt(0) == 'j' ||
         array[index].charAt(1) == 'j')
        Nodes[9]++;
      if(array[index].charAt(0) == 'k' || 
         array[index].charAt(1) == 'k')
        Nodes[10]++;
      if(array[index].charAt(0) == 'l' || 
         array[index].charAt(1) == 'l')
        Nodes[11]++;
      if(array[index].charAt(0) == 'm' || 
         array[index].charAt(1) == 'm')
        Nodes[12]++;
      if(array[index].charAt(0) == 'n' || 
         array[index].charAt(1) == 'n')
        Nodes[13]++;
      if(array[index].charAt(0) == 'o' || 
         array[index].charAt(1) == 'o')
        Nodes[14]++;
      if(array[index].charAt(0) == 'p' || 
         array[index].charAt(1) == 'p')
        Nodes[15]++;
      if(array[index].charAt(0) == 'q' || 
         array[index].charAt(1) == 'q')
        Nodes[16]++;
      if(array[index].charAt(0) == 'r' || 
         array[index].charAt(1) == 'r')
        Nodes[17]++;
      if(array[index].charAt(0) == 's' || 
         array[index].charAt(1) == 's')
        Nodes[18]++;
      if(array[index].charAt(0) == 't' || 
         array[index].charAt(1) == 't')
        Nodes[19]++;
      if(array[index].charAt(0) == 'u' || 
         array[index].charAt(1) == 'u')
        Nodes[20]++;
      if(array[index].charAt(0) == 'v' || 
         array[index].charAt(1) == 'v')
        Nodes[21]++;
      if(array[index].charAt(0) == 'w' || 
         array[index].charAt(1) == 'w')
        Nodes[22]++;
      if(array[index].charAt(0) == 'x' || 
         array[index].charAt(1) == 'x')
        Nodes[23]++;
      if(array[index].charAt(0) == 'y' || 
         array[index].charAt(1) == 'y')
        Nodes[24]++;
      if(array[index].charAt(0) == 'z' || 
         array[index].charAt(1) == 'z')
        Nodes[25]++;
    }

    /** In this project, Nodes[] will always have
     *  a length of 26, so I used 26 rather than Nodes.length
     */
    int temp = 0;
    for(int jndex = 0; jndex < 26; jndex++)
    {
      if(Nodes[jndex] != 0)
        NumberOfNodes++;
    }
  
    resize();

    // using the lndex value to convert the index into
    // the ASCII char corresponding to the correct alphabet
    int CheckedIndexes = 0;
    for(int kndex = 0; kndex < ActiveNodes.length; kndex++)
    {
      for(int lndex = CheckedIndexes; lndex < 26; lndex++)
      {
        if(Nodes[lndex] > 0)
        {
          ActiveNodes[kndex] = (char)(lndex+65);
          CheckedIndexes++;
          break;
        }
        else
          CheckedIndexes++;
      }
    }
    
    return Nodes;
  }

///////////////////////////////////////////////////////////
// Array Manipulation Methods Start                      //
///////////////////////////////////////////////////////////
  public void resize()
  {
    char[] NewArray = new char[NumberOfNodes];
    ActiveNodes = NewArray;
  }

///////////////////////////////////////////////////////////
// Array Manipulation Methods End                        //
///////////////////////////////////////////////////////////
    
  
///////////////////////////////////////////////////////////
// Start of Opening/Extracting the contents              //
// of .txt file passed                                   //
///////////////////////////////////////////////////////////
  public int countNumberOfLines() throws IOException
  {
    FileReader txt_file = new FileReader(path);
    BufferedReader bf = new BufferedReader(txt_file);
    
    String aLine;
    int numberOfLines = 0;
    
    while((aLine = bf.readLine()) != null)
    {
      numberOfLines++;
    }
    
    bf.close();
    
    return numberOfLines;
  }
  
  public String[] extractActualText() throws IOException
  {

    FileReader fr = new FileReader(path);
    BufferedReader textReader = new BufferedReader(fr);
    
    int numberOfLines = countNumberOfLines();
    Weights = new int[numberOfLines];
    Connections = new String[numberOfLines];
    String[] TextData = new String[numberOfLines];
    
    for(int index = 0; index < numberOfLines; index++)
    {
      TextData[index] = textReader.readLine();
    }
    
    textReader.close();
    
    // Storing the Connection/Weight values into arrays
    String Numbers[] = new String[numberOfLines];
    for(int index2 = 0; index2 < numberOfLines; index2++)
    {
      String[] Temp = TextData[index2].split(" ");
      Connections[index2] = Temp[0];
      Numbers[index2] = Temp[1];

    }
    
    for(int index = 0; index < TextData.length; index++)
      Weights[index] = Integer.parseInt(Numbers[index]);  
      
    return TextData; 
  }
///////////////////////////////////////////////////////////
// End of Opening/Extracting the contents                //
// of .txt file passed                                   //
///////////////////////////////////////////////////////////
  
  /**
   * This function checks if a all of the nodes
   * in the graph are marked.
   *
   * returns boolean type
   */
  public boolean checkIfAllMarked(boolean[] array)
  {
    int temp = 0;
    for(int index = 1;
            index < array.length; 
            index++)
       if(array[0] == array[index])
         temp++;
    
    if(temp == (array.length - 1))
      return true;
    else
      return false;
  }


  public void shortestPath(char origin)
  {
    char[] BackTrack = new char[NumberOfNodes];
    boolean[] Marked = new boolean[NumberOfNodes];
    boolean[] Current = new boolean[NumberOfNodes];
    int[] TotalWeight = new int[NumberOfNodes];
    Arrays.fill(Marked, false);
    Arrays.fill(Current, false);
    Arrays.fill(TotalWeight, 0);
    boolean AllMarked = false;   
  
    int[][] ThePaths = new int[NumberOfNodes][NumberOfNodes];
    // Initialize Matrix. 2147483647 is the largest value int can hold
    for(int row = 0; row < NumberOfNodes; row++)
      for(int column = 0; column < NumberOfNodes; column++)
        for(int NodeIndex = 0;
                NodeIndex < ActiveNodes.length;
                NodeIndex++)
          ThePaths[row][column] = 2147483647;

    // Fill in weights
    for(int row_find = 0;
            row_find < Connections.length; 
            row_find++)
      for(int node = 0; node < ActiveNodes.length; node++)
        if(Connections[row_find].charAt(0) ==
           Character.toLowerCase(ActiveNodes[node]))
        {
          for(int column_find = 0;
                  column_find < ActiveNodes.length;
                  column_find++)
          {
            if(Connections[row_find].charAt(1) ==
               Character.toLowerCase(ActiveNodes[column_find]))
            {
              ThePaths[node][column_find] = Weights[row_find];
              ThePaths[column_find][node] = Weights[row_find];
              break;
            }
          }        
        }
    
    // Algorithm Begin
    int TempRow = 0;
    int NextTempRow = 0;

    for(int index = 0; index < ActiveNodes.length; index++)
    {
      if(Character.toLowerCase(ActiveNodes[index]) == origin)
      {
        TempRow = index;
        Marked[index] = true;
        break;
      }
    }
    
    int small = 2147483647;
    while(AllMarked != true)
    {
    for(int index = 0; index < ActiveNodes.length; index++)
      {
        if(ThePaths[TempRow][index] < 2147483647)
          Current[index] = true;
        if(Marked[index] != true)
          if(Current[index] == true) 
          {
            if(TotalWeight[index] == 0)
            {
              TotalWeight[index] = ThePaths[TempRow][index] +
                                   TotalWeight[TempRow];
              BackTrack[index] = ActiveNodes[TempRow];
            }
            else if(TotalWeight[index] < (TotalWeight[index] +
                                          TotalWeight[TempRow] + 
                                          ThePaths[TempRow][index]))
            {}
            else
            {
              TotalWeight[index] = TotalWeight[index] +
                                   TotalWeight[TempRow] + 
                                   ThePaths[TempRow][index];
              BackTrack[index] = ActiveNodes[TempRow];
            } 
          }
      }
      
      small = 2147483647;
      for(int index = 0; index < TotalWeight.length; index++)
      {
        if(TotalWeight[index] != 0) 
          if(Marked[index] != true)
            if(TotalWeight[index] < small)
            {  
              small = TotalWeight[index];
              NextTempRow = index;
            }
      }
 
      TempRow = NextTempRow;

      Marked[TempRow] = true;
      if(checkIfAllMarked(Marked) == true)
        AllMarked = true;

      Arrays.fill(Current, false);
    }
    
    ////////////////////////////////////////////////////
    // BackTracking Algorithm
    String[] Final = new String[NumberOfNodes];
    Arrays.fill(Final,Character.toString(origin).toUpperCase());
    
    char CurrentBack;
    String Middle = "";
    char[] Temp = ActiveNodes;
    for(int index = 0; index < NumberOfNodes; index++)
    {
      if(BackTrack[index] == 'A')
        Final[index] = Final[index]+ActiveNodes[index];
      else
      {
        CurrentBack = BackTrack[index];
        int NewIndex = index;
     
        Middle = Middle+Character.toString(CurrentBack) ;
        for(int index2 = 0; index2 < NumberOfNodes; index2++)
        {
          if(Character.toLowerCase(ActiveNodes[index2]) == CurrentBack)
          {
            NewIndex = index2;
            break;
          }
        }
        CurrentBack = ActiveNodes[NewIndex];
  
        Final[index] = Final[index]+Middle+ActiveNodes[index];
      
      }
    }
    //////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////
    // Output Statements Below
    System.out.println("\nOutput File: ");
    for(int NodeIndex = 0; 
            NodeIndex < ActiveNodes.length;
            NodeIndex++)
    {        
      if(Character.toLowerCase(ActiveNodes[NodeIndex]) == origin)
        System.out.println(origin + " origin");
      else
        System.out.println(
            Character.toLowerCase(ActiveNodes[NodeIndex]) +
                            " " + TotalWeight[NodeIndex] + 
                            " " + Final[NodeIndex]
        );
    }
    //////////////////////////////////////////////////////
  }
  
  
  
  public static void main(String [] args)
  { 
    try
    {
      Graph mygraph = new Graph(args[0]);
      String[] ArrayOfLines = mygraph.extractActualText();
      
      int[] NodeTypes = mygraph.checksTypesOfNodes(ArrayOfLines);
      
       mygraph.shortestPath(args[1].charAt(0));
    }
    catch(IOException e)
    {
      System.out.println(e.getMessage());
    }    
  }
}