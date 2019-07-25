/** 
 * Creates new list with appended element. <p>O(1) complexity.
 * @param element the element to append
 * @return new instance of list with appended
 */
public ImmutableList<T> append(T element){
  ArgumentUtil.notNull(element,"element");
  return new ImmutableList<T>(element,this);
}
