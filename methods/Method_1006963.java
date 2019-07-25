/** 
 * Build a new  {@link RepeatContextSupport} and return it.
 * @see org.springframework.batch.repeat.CompletionPolicy#start(RepeatContext)
 */
@Override public RepeatContext start(RepeatContext context){
  return new RepeatContextSupport(context);
}
