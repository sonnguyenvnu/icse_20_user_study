/** 
 * Tests whether item is contained by range
 * @param index the item number
 * @return true if item is contained
 */
public boolean contains(int index){
  return index >= getFirst() && index <= getLast();
}
