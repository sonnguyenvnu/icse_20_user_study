/** 
 * Invoked when the user-defined execution method in  {@link HystrixInvokable} emits a value.
 * @param commandInstance The executing HystrixInvokable instance.
 * @param value value emitted
 * @since 1.4
 */
public <T>T onExecutionEmit(HystrixInvokable<T> commandInstance,T value){
  return value;
}
