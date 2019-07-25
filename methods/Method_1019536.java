/** 
 * Present this sketch with the given char array. If the char array is null or empty no update attempt is made and the method returns. <p>Note: this will not produce the same output hash values as the  {@link #update(String)}method but will be a little faster as it avoids the complexity of the UTF8 encoding.</p>
 * @param data The given char array.
 * @return <a href=" {@docRoot}/resources/dictionary.html#updateReturnState">See Update Return State</a>
 */
public UpdateReturnState update(final char[] data){
  if ((data == null) || (data.length == 0)) {
    return RejectedNullOrEmpty;
  }
  return hashUpdate(hash(data,getSeed())[0] >>> 1);
}
