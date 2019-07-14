/** 
 * Invoked when the fallback method in  {@link HystrixInvokable} emits a value.
 * @param commandInstance The executing HystrixInvokable instance.
 * @param value value emitted
 * @since 1.4
 */
public <T>T onFallbackEmit(HystrixInvokable<T> commandInstance,T value){
  return value;
}
