/** 
 * @param iterators a list of iterators
 * @return a concatenation of all iterators, in the order provided
 */
public static <V>Iterator<V> concat(Iterator<V>... iterators){
  if (iterators.length == 1) {
    return iterators[0];
  }
 else {
    IteratorStack<V> stack=new IteratorStack<V>();
    for (    Iterator<V> it : iterators) {
      stack.addLast(it);
    }
    return stack;
  }
}
