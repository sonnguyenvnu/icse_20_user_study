/** 
 * Present this sketch with the given long array. If the long array is null or empty no update attempt is made and the method returns.
 * @param data The given long array.
 * @return <a href=" {@docRoot}/resources/dictionary.html#updateReturnState">See Update Return State</a>
 */
public UpdateReturnState update(final long[] data){
  if ((data == null) || (data.length == 0)) {
    return RejectedNullOrEmpty;
  }
  return hashUpdate(hash(data,getSeed())[0] >>> 1);
}
