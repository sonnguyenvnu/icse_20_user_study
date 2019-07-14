/** 
 * Peeks at the next char.
 */
public char peekChar(){
  return (char)((data[position] & 0xFF) << 8 | (data[position + 1] & 0xFF));
}
