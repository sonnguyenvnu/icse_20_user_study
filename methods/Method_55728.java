/** 
 * Absolute <i>get</i> method. Reads the struct data at the specified index into the specified struct.
 * @param index the index from which the struct will be read
 * @return the struct at the specified index
 * @throws IndexOutOfBoundsException If {@code index} is negative or not smaller than the buffer's limit
 */
public SELF get(int index,T value){
  int sizeof=getElementFactory().sizeof();
  memCopy(address + Checks.check(index,limit) * sizeof,value.address(),sizeof);
  return self();
}
