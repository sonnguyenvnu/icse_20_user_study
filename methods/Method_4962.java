/** 
 * Returns a string obtained by decoding the specified range of  {@code data} using the specified{@code charsetName}. An empty string is returned if the range is invalid.
 * @param data The array from which to decode the string.
 * @param from The start of the range.
 * @param to The end of the range (exclusive).
 * @param charsetName The name of the Charset to use.
 * @return The decoded string, or an empty string if the range is invalid.
 * @throws UnsupportedEncodingException If the Charset is not supported.
 */
private static String decodeStringIfValid(byte[] data,int from,int to,String charsetName) throws UnsupportedEncodingException {
  if (to <= from || to > data.length) {
    return "";
  }
  return new String(data,from,to - from,charsetName);
}
