/** 
 * Present this sketch with the given integer array. If the integer array is null or empty no update attempt is made and the method returns.
 * @param data The given int array.
 * @return <a href=" {@docRoot}/resources/dictionary.html#updateReturnState">See Update Return State</a>
 */
public UpdateReturnState update(final int[] data){
  if ((data == null) || (data.length == 0)) {
    return RejectedNullOrEmpty;
  }
  return hashUpdate(hash(data,getSeed())[0] >>> 1);
}
