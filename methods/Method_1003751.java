/** 
 * Creates a new successful result.
 * @param value the result value
 * @param < T > the type of the result
 * @return the success result
 */
static <T>Result<T> success(T value){
  return new DefaultResult<>(value);
}
