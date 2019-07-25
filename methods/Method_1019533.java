/** 
 * Present this sketch with a long.
 * @param datum The given long datum.
 * @return <a href=" {@docRoot}/resources/dictionary.html#updateReturnState">See Update Return State</a>
 */
public UpdateReturnState update(final long datum){
  final long[] data={datum};
  return hashUpdate(hash(data,getSeed())[0] >>> 1);
}
