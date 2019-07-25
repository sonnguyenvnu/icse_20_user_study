/** 
 * Create a copy of the given array - or return null if the argument is null.
 */
private static byte[] copy(byte[] from){
  if (from != null) {
    byte[] to=new byte[from.length];
    System.arraycopy(from,0,to,0,to.length);
    return to;
  }
  return null;
}
