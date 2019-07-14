/** 
 * Peeks at the next byte as an unsigned value.
 */
public int peekUnsignedByte(){
  return (data[position] & 0xFF);
}
