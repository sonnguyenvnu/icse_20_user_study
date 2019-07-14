/** 
 * DEPRECATED: Change usages of this to  {@link #onEmit} if you want to write a hook that handles each emitted command valueor to  {@link #onSuccess} if you want to write a hook that handles success of the commandInvoked after completion of  {@link HystrixCommand} execution that results in a response.<p> The response can come either from  {@link HystrixCommand#run()} or {@link HystrixCommand#getFallback()}.
 * @param commandInstance The executing HystrixCommand instance.
 * @param response from  {@link HystrixCommand#run()} or {@link HystrixCommand#getFallback()}.
 * @return T response object that can be modified, decorated, replaced or just returned as a pass-thru.
 * @since 1.2
 */
@Deprecated public <T>T onComplete(HystrixInvokable<T> commandInstance,T response){
  return response;
}
