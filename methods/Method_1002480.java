/** 
 * Returns a callback that does nothing. Useful when the caller does not need a notification of completion.
 * @param < T > the type of the response
 * @return the empty callback
 */
@SuppressWarnings("unchecked") public static <T>Callback<T> empty(){
  return NULL_CALLBACK;
}
