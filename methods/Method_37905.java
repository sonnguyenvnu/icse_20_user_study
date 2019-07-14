/** 
 * Adapt the specified <code>Iterator</code> to the <code>Enumeration</code> interface.
 */
public static <E>Enumeration<E> asEnumeration(final Iterator<E> iter){
  return new Enumeration<E>(){
    @Override public boolean hasMoreElements(){
      return iter.hasNext();
    }
    @Override public E nextElement(){
      return iter.next();
    }
  }
;
}
