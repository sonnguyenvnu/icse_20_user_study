/** 
 * Returns the current capacity. The capacity is the amount of storage available for newly appended chars, beyond which an allocation will occur.
 * @return  the current capacity
 */
public int capacity(){
  return this.buffer.length;
}
