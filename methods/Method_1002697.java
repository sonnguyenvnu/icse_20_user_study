/** 
 * Creates a new instance from the specified byte array. The array is not copied; any changes made in the array later will be visible to  {@link HttpData}.
 * @return a new {@link HttpData}.  {@link #EMPTY_DATA} if the length of the specified array is 0.
 */
static HttpData wrap(byte[] data){
  requireNonNull(data,"data");
  if (data.length == 0) {
    return EMPTY_DATA;
  }
  return new DefaultHttpData(data,false);
}
