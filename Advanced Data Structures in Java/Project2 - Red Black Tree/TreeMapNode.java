
/*
Course:        COMP282
Semester:      SUMMER 2017
Assignment     Project 2
File Name:     TreeMapNode.java
Author:        Lo, David
Student ID:    107378113
Email Address: david.lo.239@my.csun.edu
*/

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author David Lo
 * @param <K>
 * @param <V>
 */
public class TreeMapNode<K extends Comparable<K>, V>
{

  private TreeMapNode<K, V> left;
  private TreeMapNode<K, V> right;
  private K Key;
  private V Value;
  private boolean isRed;
  
  /**
   * TreeMapNode(K key, V val) is the constructor
   * for the TreeMapNode class.
   */
  public TreeMapNode(K key, V val)
  {
    isRed = true;
    Key = key;
    Value = val;
    left = null;
    right = null;
  }
  
  /**
   * Goes through the tree and returns
   * true if there is a node with a Key
   * that matches the key passed in the argument 
   * 
   * @return boolean
   */
  public boolean containsKey(K key)
  {
    TreeMapNode<K, V> current = this;
    
    int Contains = -1;
    while(Contains != 0 && current != null)
    {
      Contains = key.compareTo(current.Key);
      
      if(Contains == 0)
        return true;
      else if(Contains < 0)
      {
        if(current.left != null)
          current = current.left;
        else
          return false;
      }
      else
      {
        if(current.right != null)
          current = current.right;
        else
          return false;
      }
    }
    
    return false;
  } 

  /**
   * This is the only function in the project
   * that allows recursion.
   *
   * Traverses the tree and checks the value of each Node
   * until the method either finds the value in a Node
   * or traverses the entire tree. 
   * 
   */  
  void containsValue(V value, AtomicBoolean found)
  {    
    TreeMapNode<K,V> current = this;
    if(current == null)
      return;
   
    if(current.Value == value)
    {
      found.set(true);
      return;
    }
    
    if(current.left != null)
      current.left.containsValue(value, found);
    if(current.right != null)
      current.right.containsValue(value, found);
    
    
  }
  
  /**
   * Using a while loop and the compareTo method
   * Looks through the tree for the key passed
   * If found, returns the value associated.
   * @return V
   */
  public V get(K key)
  {
    TreeMapNode<K, V> current = this;
        
    V result = null;
    
    int diff = -1;
    while(diff != 0 && current != null)
    {   
      diff = key.compareTo(current.Key);
  
      if(diff == 0)
        return current.Value;
      else if(diff < 0)
      {
        if(current.left != null)
          current = current.left;
        else
          return result;
      }
      else
      {
        if(current.right != null)
          current = current.right;
        else
          return result;
      }
      
    }  
  
    return result;
  }
  
  /**
   *
   * @return V
   */
  public V put(K key, V value, AtomicBoolean replaced)
  {
    TreeMapNode<K, V> gparent = null;
    TreeMapNode<K, V> parent = null;
    TreeMapNode<K, V> current = this;
    
    if(parent == null && gparent == null)
      current.isRed = false;
  
    if(current.is4Node() == true)
      fix4NodeRoot(current);
         
    replaced.set(false);
    
    V result = null;
    int diff = -1;
    
    while(diff != 0 && current != null)
    {
      diff = key.compareTo(current.Key);
      if(diff == 0)
      {
        replaced.set(true);
        result = current.Value;
        current.Value = value;
        return result;
      }
      else if(diff < 0)
      {
        if(current.left == null)
        {
          current.left = new TreeMapNode<>(key, value);
          if(parent != null)
            if(parent.is4Node() == true)
            {  
              if(gparent == null)
                fix4NodeRoot(parent);
              else 
                fix4Node(parent, gparent);
            }
          result = null;
          return result;
        }
        else
        {
          if(current.left.is4Node() == false)
          {
            gparent = parent;
            parent = current;
            current = current.left;
          }
          else
            fix4Node(current.left, current);
        }
      }
      // Diff > 0
      else
      {
        if(current.right == null)
        {
          current.right = new TreeMapNode<>(key, value);
          System.out.println("Inserted: "+key);
          if(parent != null)
            if(parent.is4Node() == true)
            {
              if(gparent == null)
              {
                fix4NodeRoot(parent);
              }
              else 
                fix4Node(parent, gparent);
            }

          result = null;
          return result;
        }
        else
        {
          if(current.right.is4Node() == false)
          {
            gparent = parent;
            parent = current;
            current = current.right;
          }
          else
            fix4Node(current.right, current);
        }
      }
    }
    
    return result;
  }
  
  /**
   *
   * @return V
   */
  public V remove(K key, 
                  AtomicBoolean removed, 
                  AtomicBoolean RootRemoved)
  {
    TreeMapNode<K, V> gparent = null;
    TreeMapNode<K, V> parent = null;
    TreeMapNode<K, V> current = this;
    
    if(parent == null && gparent == null)
      current.isRed = false;
  
    // A quick check if there is only one Node in tree
    if(current.is2Node() == true)
    {
      if(current.right == null && current.left == null) 
      {
        if(current.Key == key)
        {
          RootRemoved.set(true);
          return current.Value;
        }
      }
    }  
   
    removed.set(false);
    V result = null;
    int diff = -1;

      TreeMapNode<K,V> InOrderSuccessorParent = parent;
      TreeMapNode<K,V> InOrderSuccessor = current;
    
    while(diff != 0 && current != null)
    {
      diff = key.compareTo(current.Key);

      if(diff == 0)
      {
        if(current.right == null && current.left == null)
        { 
          if(parent != null)
          {
            if(current == parent.right)
            {
              parent.right = null;
              removed.set(true);
              return current.Value;
            }
            else
            {
              parent.left = null;
              removed.set(true);
              return current.Value;
            }
          }
        }
        
        
        InOrderSuccessorParent = findInOrderSuccessorParent(current, 
                                                            parent,
                                                            gparent);
        if(InOrderSuccessorParent == null)
        {
          System.out.println("Current Key: " + current.Key);
          System.out.println("IOSParent is NULL");
        }
        boolean isSuccessor = true;
        
        int CheckIOS=InOrderSuccessorParent.Key.compareTo(current.Key);
        
        if(CheckIOS == 0)
        {
          if(current.right != null)
          {
            InOrderSuccessor = current.right;
            isSuccessor = true;
          }
          else
          {
            if(current.left != null)
            {
              InOrderSuccessor = current.left;
              isSuccessor = false;
            }
          }
        }
        else if(CheckIOS > 0)
        {
          isSuccessor = true;
          InOrderSuccessor = InOrderSuccessorParent.left;
        }
        else
        {
          isSuccessor = false;
          InOrderSuccessor = InOrderSuccessorParent.right;
        }
                  
        swap(current, InOrderSuccessor);
        if(isSuccessor == true)
          InOrderSuccessorParent.left = InOrderSuccessor.right;
        else
          InOrderSuccessorParent.right = InOrderSuccessor.left;
      
        removed.set(true);
        return null;
      }
      else if(diff > 0)
      {
        if(current.right != null)
          if(current.right.is2Node() == true && parent != null)
            current.right.fix2Node(current.right, current, parent);
          else
          {
            gparent = parent;
            parent = current;
            current = current.right;
          }          
        else
          current = null;
      }
      // Diff < 0
      else
      {
        if(current.left != null)
          if(current.left.is2Node() == true)
            current.left.fix2Node(current.left, current, parent);
          else
          {
            gparent = parent;
            parent = current;
            current = current.left;
          }          
        else
          current = null;
      }
    }
  
    return result;
  }
  
  public void reColor(TreeMapNode<K, V> current)
  {
    if(current.isRed == true)
      current.isRed = false;
    else
      current.isRed = true; 
  }
  
  /**
   *
   * @return boolean
   */
  public boolean is4Node()
  {
    TreeMapNode<K, V> current = this;
    boolean result = false;
    if(current.isRed == false)
    {
      // Left and right child are red
      if(current.right != null && current.left != null)
        if((current.right.isRed==true)&&(current.left.isRed==true))
          result = true;
  
      // Checks if Right Right are Reds or if Right Left are Reds
      if(current.right != null)
      {
        if(current.right.isRed == true)
        {
          if(current.right.left != null)    
            if(current.right.left.isRed == true)
              result = true;
          
          if(current.right.right != null)
            if(current.right.right.isRed == true)
              result = true;
        }
      }
      // Checks if Left Right are Reds or if Left Left are Reds
      
      if(current.left != null)
      {
        if(current.left.isRed == true)
        {
          if(current.left.left != null)    
            if(current.left.left.isRed == true)
              result = true;
          
          if(current.left.right != null)
            if(current.left.right.isRed == true)
              result = true;    
        }
      }
    }
    return result;
  }
  
  /** 
   * Since the root node's parent pointer is NULL, 
   * I created this additional method just to fix
   * the root if the root is the four node to avoid 
   * the NullPointerException Error if the Root Node is a 4-Node.
   */
  public void fix4NodeRoot(TreeMapNode<K, V> current)
  {
    TreeMapNode<K,V> temp;
    // Triangle-shaped 4Node. Root Node is always Black.
    if(current.right != null && current.left != null)
    {
      if((current.right.isRed == true)&&(current.left.isRed == true))
      {
        //reColor(current);
        reColor(current.right);
        reColor(current.left);
      }
    }
    //Black with Left and Left Red
    else if(current.left != null&&current.left.left != null)
    {
      if(current.left.isRed==true && current.left.left.isRed==true)
      {
        temp = current.left;
        current.left = temp.right;
        temp.right = current;
        current = temp;
        reColor(current);
        reColor(current.right);
      }
    }
    // Black with Left Right Red
    else if(current.left != null && current.left.right != null)
    {
      if(current.left.isRed==true && current.left.right.isRed==true)
      {
        temp = current.left.right;
        current.left.right = temp.left;
        temp.left = temp.right;
        temp.right = current.right;
        current.right = temp;
        swap(current, temp);
      } 
    }
     // Black with Right Left Red
    else if(current.right != null && current.right.left != null)
      if(current.right.isRed==true && current.right.left.isRed==true)
      {
        temp = current.right.left;
        current.right.left = temp.right;
        temp.right = current.right;
        current.right = temp.left;
        temp.left = current;
        current = temp;
        reColor(current);
        reColor(current.left);
      }
    //Black with Right and Right Red
    else
    {
      temp = current.right;
      current.right = temp.left;
      temp.left = current;
      reColor(current);
      reColor(current.left);
    }

  }
  
  public void fix4Node(TreeMapNode<K, V> current,
                       TreeMapNode<K, V> parent)
  {
    // Triangle-shaped 4Node
    if(current.right != null && current.left != null)
    {
      if((current.right.isRed == true)&&(current.left.isRed == true))
      {
        reColor(current);
        reColor(current.right);
        reColor(current.left);
      }
    }
    //Black with Right and Right Red
    else if(current.right != null && current.right.right != null)
    {
      if(current.right.isRed==true && current.right.right.isRed==true)
        leftRotate(parent, current);
    }
    //Black with Left and Left Red
    else if(current.left != null && current.left.left != null)
    {
      if(current.left.isRed==true && current.left.left.isRed==true)
        rightRotate(parent, current);
    }
    // Black with Right Left Red
    else if(current.right != null && current.right.left != null)
    {
      if(current.right.isRed==true && current.right.left.isRed==true)
      {
        rightRotate(current, current.right);
        leftRotate(parent, current);
      }
    }
    // Black with Left Right Red
    else
    {
      leftRotate(current, current.left);
      rightRotate(parent, current);
      
    } 
  }

  /**
   * Checks if the Destination node is a 2-Node
   * Used for remove method
   *
   * @return boolean
   */
  public boolean is2Node()
  {
    TreeMapNode<K,V> current = this;
    boolean result = false;
    
    if(current.isRed == false)
    {
      if(current.right == null || current.right.isRed == false)
        if(current.left == null || current.left.isRed == false)
          result = true;
    }
    return result;
  }
  
  /**
   * I implemented the cases discussed during lecture
   * to fix any 2-Nodes we may want to step on 
   * during the remove method
   *
   */
  public void fix2Node(TreeMapNode<K,V> current, 
                       TreeMapNode<K,V> parent, 
                       TreeMapNode<K,V> gparent)
  {
    if(current == parent.left)
    {
      // Case A and Case B and Case C since 
      // Case A & C are both rotate right then rotate left
      if(parent.isRed == true && parent.right.isRed == false)
      {
        if(parent.right.left.isRed == true)
          rightRotate(parent, parent.right);
        leftRotate(gparent, parent);
      }
      //Case D
      if(parent.isRed == true && parent.right.is2Node() == true)
      {
        reColor(parent);
        reColor(current);
        reColor(parent.right);
      }
    }
    else
    {
      // Case E-H which are the mirror cases of A-D
      // that were discussed during lecture
      
      //If not Case E or G, then it is Case F
      if(parent.isRed == true && parent.left.isRed == false)
      {
        if(parent.left.right.isRed == true)
          leftRotate(parent, parent.left);
        rightRotate(gparent, parent);
      }
      //Case H
      if(parent.isRed == true && parent.left.is2Node() == true)
      {
        reColor(parent);
        reColor(current);
        reColor(parent.left);
      }
    }
    
    // Merge Parent down to create 4-Node
    if(current.right != null && current.left != null)
    {
      if(current.right.isRed==false && current.left.isRed==false)
      {
        reColor(current.right);
        reColor(current.left);
      }
     }
     else
       System.out.println("Curent right or current left is NULL"); 
  }

  public void fix2NodeRoot(TreeMapNode<K,V> current)
  {
    TreeMapNode<K,V> temp = current;
    
    // Root is always Black
    // So just check if both Child are Blacks or Both Null
    // Since all paths must have same number of blacks
    if(current.right.isRed == false && current.left.isRed == false)
    {
      reColor(current.right);
      reColor(current.left);
    }  
  }

  public void rightRotate(TreeMapNode<K, V> current,
                          TreeMapNode<K, V> destination)
  {
    boolean isRight;
    if(destination == current.right)
    {
      current.right = destination.left;
      isRight = true;
      reColor(current.right);
    }
    else
    {
      current.left = destination.left;
      isRight = false;
      reColor(current.left);
    }
    destination.left = destination.left.right;
    reColor(destination);
    
    if(isRight == true)
    {
      current.right.right = destination;
      destination = current.right;
    }
    else
    {
      current.left.right = destination;
      destination = current.left;
    }  
    
  }
  
  /**
   * Does the left rotate from when
   * we learned AVL trees and 
   * recolors when necessary
   */
  public void leftRotate(TreeMapNode<K, V> current,
                         TreeMapNode<K, V> destination)
  {
    boolean isRight;
    if(destination == current.right)
    {
      current.right = destination.right;
      isRight = true;
      reColor(current.right);
    }
    else
    {
      current.left = destination.right;
      isRight = false;
      reColor(current.left);
    }
    
    destination.right = destination.right.left;
    reColor(destination);
    
    if(isRight == true)
    {
      current.right.left = destination;
      destination = current.right;
    }
    else
    {
      current.left.left = destination;
      destination = current.left;
    }

  }
  
  /**
   * Finds InOrderSuccessorParent first. If no right Node,
   * then do InOrderPredecessorParent
   *
   * @return TreeMapNode<K,V>
   */
  public TreeMapNode<K,V> findInOrderSuccessorParent
                          (TreeMapNode<K,V> current, 
                           TreeMapNode<K,V> parent,
                           TreeMapNode<K,V> gparent)
  {
    TreeMapNode<K,V> temp = current;

    if(temp.right != null)
    {
      if(temp.right.is4Node() == true)
        fix4Node(temp, parent);
      gparent = parent;
      parent = temp;
      temp = temp.right;
      while(temp.left != null)
      {
        if(temp.left.is4Node() == true)
          fix4Node(temp, parent);
        gparent = parent;
        parent = temp;
        temp = temp.left;
      }
      return parent;  
    }
    else if(temp.left != null)
    {
      if(temp.left.is4Node() == true)
        fix4Node(temp, parent);
      gparent = parent;
      parent = temp;
      temp = temp.left;
      while(temp.right != null)
      {
        if(temp.right.is4Node() == true)
          fix4Node(temp, parent);
        gparent = parent;
        parent = temp;
        temp = temp.right;
      } 
      return parent;
    }
    else
      return parent;
  }
  
  /**
   * Swaps the key and value of the two nodes passed.
   * This is mainly a helper function for the remove method
   * after finding the InOrderSuccessor
   */
  public void swap(TreeMapNode<K,V> first, TreeMapNode<K,V> second)
  {
    K tempKey = null;
    V tempValue = null;
    tempKey = second.Key;
    tempValue = second.Value;
    second.Key = first.Key;
    second.Value = first.Value;
    first.Key = tempKey;
    first.Value = tempValue;
  }  
}