/** 
 * Returns the current capacity. The capacity is the amount of storage available for newly appended bytes, beyond which an allocation will occur.
 * @return  the current capacity
 */
public int capacity(){
  return this.buffer.length;
}
