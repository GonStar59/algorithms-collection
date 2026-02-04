package aed.treap;

/**
 * Basic set methods.
 */
public interface BasicSet<E> extends Iterable<E> {

    /**
     * Returns true if the set is not empty, and false otherwise.
     * @return true if the set is not empty, and false otherwise.
     */
    public boolean isEmpty();                  

    /**
     * Returns the size of the set.
     * @return the size of the set.
     */
    public int size();                         
 
    /**
     * Adds an element to the set.
     * @return true if the set already contained the element.
     * @throws IllegalArgumentException si {@code elem} es {@code null}.
     */
    public boolean add(E elem);                
                                        
                                        
    /**
     * Removes an element from the set.
     * @return true if the set contained the element, and false otherwise.
     * @throws IllegalArgumentException si {@code elem} es {@code null}.
     */
    public boolean remove(E elem);             
                                        
                                        
    /**
     * Checks if the set contains an element.
     * @return true if the set contained the element, and false otherwise.
     * @throws IllegalArgumentException si {@code elem} es {@code null}.
     */
    public boolean contains(E elem);

}
