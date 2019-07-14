/** 
 * Reads the next four bytes as an unsigned value.
 */
public long readUnsignedInt(){
  return (data[position++] & 0xFFL) << 24 | (data[position++] & 0xFFL) << 16 | (data[position++] & 0xFFL) << 8 | (data[position++] & 0xFFL);
}
