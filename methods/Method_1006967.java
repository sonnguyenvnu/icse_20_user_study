/** 
 * Reset the counter.
 * @see org.springframework.batch.repeat.CompletionPolicy#start(RepeatContext)
 */
@Override public RepeatContext start(RepeatContext context){
  return new SimpleTerminationContext(context);
}
