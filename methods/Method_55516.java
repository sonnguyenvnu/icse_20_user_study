/** 
 * Returns the memory address at the current buffer position. 
 */
@Override public long address(){
  return address + Integer.toUnsignedLong(position) * sizeof();
}
