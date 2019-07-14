/** 
 * Invoked when  {@link HystrixInvokable} emits a value.
 * @param commandInstance The executing HystrixInvokable instance.
 * @param value value emitted
 * @since 1.4
 */
public <T>T onEmit(HystrixInvokable<T> commandInstance,T value){
  return value;
}
