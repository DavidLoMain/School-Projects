
/*
Course:        COMP282
Semester:      SUMMER 2017
Assignment     Project 2
File Name:     TreeMap.java
Author:        Lo, David
Student ID:    107378113
Email Address: david.lo.239@my.csun.edu
*/

import java.lang.Comparable;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 *
 * @author David Lo
 * @param <K>
 * @param <V>
 */
class TreeMap<K extends Comparable<K>, V>
{
  private TreeMapNode<K,V> root;
  private int size;

  public TreeMap()
  {
    root = null;
    size = 0;
  }

  public void clear()
  {
    root = null;
    size = 0;
  }

  /**
   *
   * @return boolean
   */
  public boolean containsKey(K key)
  {
    boolean result = false;  

    if(root != null)
      result = root.containsKey(key);
   
    return result;
  }
  
  /**
   *
   * @return boolean
   */
  public boolean containsValue(V value)
  { 
    AtomicBoolean found = new AtomicBoolean(false);
    if(root != null)
      root.containsValue(value, found);
      
    if(found.get() == true)
      return true;
    else
      return false;
  }

  /**
   *
   * @return V
   */
  public V get(K key)
  {
    V result = null;
    if(root != null)
      result = root.get(key);
           
    return result;
  }

  /**
   *
   * @return V
   */  
  public V put(K key, V value)
  {
    if(root == null)
    {
      root = new TreeMapNode<>(key, value);
      root.reColor(root);
     
      size++;
      return null;
    }
    else
    {
      AtomicBoolean replaced = new AtomicBoolean(false);
    
      V result;
      result = root.put(key, value, replaced);
      if(!replaced.get())
        size++;
        
      return result;
    }
  }
  
  /**
   *
   * @return V
   */
  public V remove(K key)
  {
    if(root == null)
      return null;
    else
    {
      AtomicBoolean RootRemoved = new AtomicBoolean(false);
      AtomicBoolean removed = new AtomicBoolean(false);
      V result = root.remove(key, removed, RootRemoved);
      
        
      if(RootRemoved.get())
        clear();
      if(removed.get())
        size--;
   
      return result;
    }
  }
   
  /**
   * Checks the size of the tree then returns
   * true if the tree is currently empty.
   *
   * @return boolean
   */
  public boolean isEmpty()
  {
    if(root == null && size() == 0)
      return true;
      
    return false;
  }

  /**
   * Returns the size of the tree.
   * Does not violate Abstraction and Opaqueness
   *
   * @return int
   */
  public int size()
  {
    return size;
  }

  public static void main(String[] args)
  {

  }
}