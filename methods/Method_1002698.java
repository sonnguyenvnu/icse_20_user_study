/** 
 * Creates a new instance from the specified byte array,  {@code offset} and {@code length}. The array is not copied; any changes made in the array later will be visible to  {@link HttpData}.
 * @return a new {@link HttpData}.  {@link #EMPTY_DATA} if {@code length} is 0.
 * @throws ArrayIndexOutOfBoundsException if {@code offset} and {@code length} are out of bounds
 */
static HttpData wrap(byte[] data,int offset,int length){
  requireNonNull(data,"data");
  if (offset < 0 || length < 0 || offset > data.length - length) {
    throw new ArrayIndexOutOfBoundsException("offset: " + offset + ", length: " + length + ", data.length: " + data.length);
  }
  if (length == 0) {
    return EMPTY_DATA;
  }
  if (data.length == length) {
    return wrap(data);
  }
  return new ByteRangeHttpData(data,offset,length,false);
}
