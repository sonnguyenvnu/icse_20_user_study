/** 
 * Start the clock on the timeout.
 * @see org.springframework.batch.repeat.CompletionPolicy#start(RepeatContext)
 */
@Override public RepeatContext start(RepeatContext context){
  return new TimeoutBatchContext(context);
}
