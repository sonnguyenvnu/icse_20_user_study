/** 
 * Returns the memory address at the specified buffer position. 
 */
public long address(int position){
  return address + Integer.toUnsignedLong(position) * sizeof();
}
