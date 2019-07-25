/** 
 * Create this sketch with the given String. The string is converted to a byte array using UTF8 encoding. If the string is null or empty no create attempt is made and the method returns null. <p>Note: this will not produce the same hash values as the  {@link #create(char[])}method and will generally be a little slower depending on the complexity of the UTF8 encoding. </p>
 * @param datum The given String.
 * @return a SingleItemSketch or null
 */
public static SingleItemSketch create(final String datum){
  if ((datum == null) || datum.isEmpty()) {
    return null;
  }
  final byte[] data=datum.getBytes(UTF_8);
  return new SingleItemSketch(hash(data,DEFAULT_UPDATE_SEED)[0] >>> 1);
}
