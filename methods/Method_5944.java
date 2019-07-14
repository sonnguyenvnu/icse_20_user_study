/** 
 * Reads the next two bytes as a signed value.
 */
public short readLittleEndianShort(){
  return (short)((data[position++] & 0xFF) | (data[position++] & 0xFF) << 8);
}
