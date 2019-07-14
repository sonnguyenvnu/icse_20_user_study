/** 
 * Reads the next three bytes as a signed value.
 */
public int readInt24(){
  return ((data[position++] & 0xFF) << 24) >> 8 | (data[position++] & 0xFF) << 8 | (data[position++] & 0xFF);
}
