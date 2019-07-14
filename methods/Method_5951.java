/** 
 * Reads the next four bytes as a signed value
 */
public int readInt(){
  return (data[position++] & 0xFF) << 24 | (data[position++] & 0xFF) << 16 | (data[position++] & 0xFF) << 8 | (data[position++] & 0xFF);
}
