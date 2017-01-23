
public class MyStack<T> {
	/**
	 * The MyStack class creates a stack that allows the user to add any amount of 
	 * elements to. It uses an linked list as the underlying  structure. The 
	 * object will throw an exception if the user tries to peek/pop an empty stack.
	 * @author Raymond Schooley
	 * @version 1/23/2017 
	 */

	    private int size;
	    private Node<T> top;


	    /**
	     * Constructor for objects of class MyStack. Given values to fields is 
	     * a O(k) constant time operation.
	     */
	    public MyStack()
	    {
	       size=0;
	       top = new Node<T>(null, null);
	    }

	  
	    /**
	     * Adds an element to top of the stack.  Reassign some references 
	     * is a O(k) constant time operation.
	     * @param T Type to be added.
	     */
	    public void push(T t)
	    {
	      //if this is the first link to be added we'll handle that special
	      if(top.item==null)
	      {
	          top.item = t;
	      }
	      //For normal cases we need to put a new element at the top of the stack
	      else
	      {
	          Node<T> curs = new Node<T>(top, t);
	          top = curs;
	      }
	      size++;
	      
	       
	    }

	     /**
	     * Removes the last element added to the stack. 
	     * Removing from this stack entails a couple of reassignments so it runs
	     *  on O(k) constant time.
	     * @return T Item at the top of the stack
	     * @throws EmptyStackException Cannot remove an element from empty stack.
	     */
	    public T pop() throws EmptyStackException
	    {
	       T t = top.item;
	       if(size==0)
	       {
	           throw new EmptyStackException("Stack is empty");
	       }
	       
	       top = top.next;
	       size--;
	       return t;
	       
	    }
	    
	     /**
	     * Allows user to view element at the top  of the stack. Remove a copy 
	     * of top.item is a O(k) constant time operation.
	     * @return T Item at the top of the stack.
	     * @throws EmptyStackException Cannot view an element from an empty stack.
	     */
	    public T peek() throws EmptyStackException
	    {
	       if(size==0)
	       {
	           throw new EmptyStackException("Stack is empty");
	       }
	       
	       return top.item;
	    }
	    
	     /**
	     * Allows user to query whether or not the stack has 
	     * elements in it.  Boolean evaluation with fields is a O(k) constant
	     * time operation.
	     * @return boolean Whether or not the stack has elements in it.
	     */
	    public boolean hasNext()
	    {
	        return size!=0;
	    }
	    
	    /**
	     * Allow user to query the current size of the stack. Return 
	     * copy of field value is a O(k) constant time operation
	     * @return int Current size of the stack.
	     */
	    public int size()
	    {
	        return size;
	    }
	    
	    /**
	     * Allow user to print out current state of stack.  The current 
	     * implementation generates a new String with every call so this is a 
	     * O(n) linear-time operation.
	     * @return String String representation of object.
	     */
	    public String toString()
	    {
	        Node<T> curs = top;
	        String str="["+size+": ";
	        
	        while(curs.next!=null)
	        {
	            str += curs.item.toString()+", ";
	            curs = curs.next;
	        }
	        
	       //Don't write out the null if the stack is empty
	       if(top.item != null)
	       {
	           str += curs.item;
	       }
	       return str + "]";
	    }
	}

	/**
	 * The Node class is used to assist implementing stack with 
	 * linked lists.
	 * 
	 * @author Raymond Schooley 
	 * @version 1/23/2017
	 */
	class Node<T>
	{
		T item;
		Node<T> next;

		/**
		 * Constructor for objects of class Node
		 */
		public Node(Node<T> next,  T item)
		{
			//All need is an item a way to 
			this.next=next;
			this.item=item;
		}

	}

	 class EmptyStackException extends java.lang.Exception {
		    
		/**Auto-generated serial ID*/
		private static final long serialVersionUID = -335117072136990522L;

			public EmptyStackException(String msg) {
		        super(msg);
		    }
		    
		}
	

