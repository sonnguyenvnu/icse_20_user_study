/** 
 * Reads the next three bytes as an unsigned value.
 */
public int readUnsignedInt24(){
  return (data[position++] & 0xFF) << 16 | (data[position++] & 0xFF) << 8 | (data[position++] & 0xFF);
}
