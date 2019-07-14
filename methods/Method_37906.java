/** 
 * Adapt the specified <code>Enumeration</code> to the <code>Iterator</code> interface.
 */
public static <E>Iterator<E> asIterator(final Enumeration<E> e){
  return new Iterator<E>(){
    @Override public boolean hasNext(){
      return e.hasMoreElements();
    }
    @Override public E next(){
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return e.nextElement();
    }
    @Override public void remove(){
      throw new UnsupportedOperationException();
    }
  }
;
}
