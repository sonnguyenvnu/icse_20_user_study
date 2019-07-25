/** 
 * Creates a list which repeatedly uses the element function for each lookup.
 * @param size       the size of the list
 * @param elementFn  a function which returns the list for the given element
 * @param iteratorFn a function which generates an iterator for the list
 * @return a list
 */
public static <V>IList<V> from(long size,LongFunction<V> elementFn,Supplier<Iterator<V>> iteratorFn){
  return new IList<V>(){
    @Override public int hashCode(){
      return (int)Lists.hash(this);
    }
    @Override public boolean equals(    Object obj){
      if (obj instanceof IList) {
        return Lists.equals(this,(IList<V>)obj);
      }
      return false;
    }
    @Override public String toString(){
      return Lists.toString(this);
    }
    @Override public V nth(    long idx){
      if (idx < 0 || size <= idx) {
        throw new IndexOutOfBoundsException(idx + " must be within [0," + size + ")");
      }
      return elementFn.apply(idx);
    }
    @Override public Iterator<V> iterator(){
      return iteratorFn.get();
    }
    @Override public IList<V> clone(){
      return this;
    }
    @Override public long size(){
      return size;
    }
  }
;
}
