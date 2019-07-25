/** 
 * Returns the number of elements that have not yet been read or written 
 */
public int size(){
  return limit - position;
}
