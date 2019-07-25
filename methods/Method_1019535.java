/** 
 * Present this sketch with the given byte array. If the byte array is null or empty no update attempt is made and the method returns.
 * @param data The given byte array.
 * @return <a href=" {@docRoot}/resources/dictionary.html#updateReturnState">See Update Return State</a>
 */
public UpdateReturnState update(final byte[] data){
  if ((data == null) || (data.length == 0)) {
    return RejectedNullOrEmpty;
  }
  return hashUpdate(hash(data,getSeed())[0] >>> 1);
}
