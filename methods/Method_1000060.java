/** 
 * Creates a new instance that wraps the given hash value.
 * @param rawHashBytes the raw hash bytes to wrap
 * @return a new instance
 * @throws IllegalArgumentException if the given array length is not exactly 32
 */
@SuppressWarnings("deprecation") public static Sha256Hash wrap(byte[] rawHashBytes){
  return new Sha256Hash(rawHashBytes);
}
