/** 
 * Returns the binary value with the specified key. <p> JSON itself has no notion of a binary. This extension complies to the RFC-7493. THe byte array is Base64 encoded binary.
 */
public byte[] getBinary(final String key){
  String encoded=(String)map.get(key);
  return encoded == null ? null : Base64.getDecoder().decode(encoded);
}
