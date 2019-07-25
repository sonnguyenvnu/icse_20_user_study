/** 
 * @param it an iterator
 * @param f a function which transforms values
 * @return an iterator which yields the transformed values
 */
public static <U,V>Iterator<V> map(Iterator<U> it,Function<U,V> f){
  return new Iterator<V>(){
    @Override public boolean hasNext(){
      return it.hasNext();
    }
    @Override public V next(){
      return f.apply(it.next());
    }
  }
;
}
