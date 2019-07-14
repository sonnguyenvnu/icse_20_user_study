/** 
 * Reads the next three bytes as a signed value in little endian order.
 */
public int readLittleEndianInt24(){
  return (data[position++] & 0xFF) | (data[position++] & 0xFF) << 8 | (data[position++] & 0xFF) << 16;
}
