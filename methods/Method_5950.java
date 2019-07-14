/** 
 * Reads the next four bytes as an unsigned value in little endian order.
 */
public long readLittleEndianUnsignedInt(){
  return (data[position++] & 0xFFL) | (data[position++] & 0xFFL) << 8 | (data[position++] & 0xFFL) << 16 | (data[position++] & 0xFFL) << 24;
}
