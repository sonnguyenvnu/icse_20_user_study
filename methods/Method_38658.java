/** 
 * Returns the byte[] at position  {@code pos} in the array.<p> JSON itself has no notion of a binary, so this method assumes there is a String value and it contains a Base64 encoded binary, which it decodes if found and returns.
 */
public byte[] getBinary(final int pos){
  String val=(String)list.get(pos);
  if (val == null) {
    return null;
  }
  return Base64.getDecoder().decode(val);
}
