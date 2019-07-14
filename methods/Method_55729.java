/** 
 * Absolute <i>put</i> method&nbsp;&nbsp;<i>(optional operation)</i>. <p>Writes the specified struct into this buffer at the specified index.</p>
 * @param index the index at which the struct will be written
 * @param value the struct value to be written
 * @return This buffer
 * @throws IndexOutOfBoundsException        If {@code index} is negative or not smaller than the buffer's limit
 * @throws java.nio.ReadOnlyBufferException If this buffer is read-only
 */
public SELF put(int index,T value){
  int sizeof=getElementFactory().sizeof();
  memCopy(value.address(),address + Checks.check(index,limit) * sizeof,sizeof);
  return self();
}
