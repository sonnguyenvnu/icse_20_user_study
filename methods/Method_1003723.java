/** 
 * Signals this downstream, based on the given result.
 * @param result the result to signal
 */
default void accept(Result<? extends T> result){
  if (result.isError()) {
    error(result.getThrowable());
  }
 else {
    success(result.getValue());
  }
}
