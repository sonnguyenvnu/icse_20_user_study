/** 
 * Reads the next byte as an unsigned value.
 */
public int readUnsignedByte(){
  return (data[position++] & 0xFF);
}
