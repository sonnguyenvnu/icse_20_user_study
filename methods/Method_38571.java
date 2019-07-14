/** 
 * Returns  {@code true} if token is expired.
 */
public boolean expired(){
  if (until == 0) {
    return false;
  }
  return System.currentTimeMillis() > until;
}
