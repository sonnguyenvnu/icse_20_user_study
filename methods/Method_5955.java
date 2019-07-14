/** 
 * Reads the next four bytes, returning the integer portion of the fixed point 16.16 integer.
 */
public int readUnsignedFixedPoint1616(){
  int result=(data[position++] & 0xFF) << 8 | (data[position++] & 0xFF);
  position+=2;
  return result;
}
