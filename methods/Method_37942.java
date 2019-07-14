/** 
 * Safe  {@code charAt} that returns 0 when ndx is out of boundaries.
 */
private static char charAt(final String string,final int ndx){
  if (ndx >= string.length()) {
    return 0;
  }
  return string.charAt(ndx);
}
