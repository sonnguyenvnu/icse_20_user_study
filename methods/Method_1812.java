/** 
 * Specifies a bytes range to request only the bytes from a specified index up to the end of the data.
 * @param from the first byte to request, must be positive or zero
 */
public static BytesRange from(int from){
  Preconditions.checkArgument(from >= 0);
  return new BytesRange(from,TO_END_OF_CONTENT);
}
