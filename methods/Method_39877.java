/** 
 * Get the underlying character array or <code>null</code> if writer not used.
 */
public char[] toCharArray(){
  if (writer == null) {
    return null;
  }
  return writer.toCharArray();
}
