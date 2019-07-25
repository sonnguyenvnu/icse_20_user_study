/** 
 * Advance cursor by one and return the character that was at that position
 * @throws IndexOutOfBoundsException if cursor is already at the end 
 */
public char advance(){
  char result=string.charAt(cursor);
  cursor=Math.min(string.length(),cursor + 1);
  return result;
}
