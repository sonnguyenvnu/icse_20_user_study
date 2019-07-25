/** 
 * Discard an arbitrary number of elements from the top of the stack.
 */
public void discard(int count){
  assert elementsCount >= count;
  elementsCount-=count;
  Arrays.fill(buffer,elementsCount,elementsCount + count,null);
}
