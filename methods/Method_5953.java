/** 
 * Reads the next eight bytes as a signed value.
 */
public long readLong(){
  return (data[position++] & 0xFFL) << 56 | (data[position++] & 0xFFL) << 48 | (data[position++] & 0xFFL) << 40 | (data[position++] & 0xFFL) << 32 | (data[position++] & 0xFFL) << 24 | (data[position++] & 0xFFL) << 16 | (data[position++] & 0xFFL) << 8 | (data[position++] & 0xFFL);
}
