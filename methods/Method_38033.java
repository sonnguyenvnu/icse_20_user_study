/** 
 * Returns String characters in most performing way. If possible, the inner <code>char[]</code> will be returned. If not, <code>toCharArray()</code> will be called. Returns <code>null</code> when argument is <code>null</code>.
 */
public static char[] getChars(final String string){
  if (string == null) {
    return null;
  }
  if (!HAS_UNSAFE || !JoddCore.unsafeUsageEnabled) {
    return string.toCharArray();
  }
  return UnsafeInternal.unsafeGetChars(string);
}
