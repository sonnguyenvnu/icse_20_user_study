/** 
 * Reads the next three bytes as an unsigned value in little endian order.
 */
public int readLittleEndianUnsignedInt24(){
  return (data[position++] & 0xFF) | (data[position++] & 0xFF) << 8 | (data[position++] & 0xFF) << 16;
}
