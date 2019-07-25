/** 
 * Present this sketch with the given String. The string is converted to a byte array using UTF8 encoding. If the string is null or empty no update attempt is made and the method returns. <p>Note: this will not produce the same output hash values as the  {@link #update(char[])}method and will generally be a little slower depending on the complexity of the UTF8 encoding. </p>
 * @param datum The given String.
 * @return <a href=" {@docRoot}/resources/dictionary.html#updateReturnState">See Update Return State</a>
 */
public UpdateReturnState update(final String datum){
  if ((datum == null) || datum.isEmpty()) {
    return RejectedNullOrEmpty;
  }
  final byte[] data=datum.getBytes(UTF_8);
  return hashUpdate(hash(data,getSeed())[0] >>> 1);
}
