/** 
 * Tells whether there are any elements between the current position and the limit.
 * @return {@code true} if, and only if, there is at least one element remaining in this buffer
 */
public boolean hasRemaining(){
  return position < limit;
}
