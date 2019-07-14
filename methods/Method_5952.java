/** 
 * Reads the next four bytes as a signed value in little endian order.
 */
public int readLittleEndianInt(){
  return (data[position++] & 0xFF) | (data[position++] & 0xFF) << 8 | (data[position++] & 0xFF) << 16 | (data[position++] & 0xFF) << 24;
}
