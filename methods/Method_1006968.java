/** 
 * Increment the counter in the context.
 * @see org.springframework.batch.repeat.CompletionPolicy#update(RepeatContext)
 */
@Override public void update(RepeatContext context){
  ((SimpleTerminationContext)context).update();
}
