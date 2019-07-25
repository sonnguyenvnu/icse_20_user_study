/** 
 * Increment the context so the counter is up to date. Do nothing else.
 * @see org.springframework.batch.repeat.CompletionPolicy#update(org.springframework.batch.repeat.RepeatContext)
 */
@Override public void update(RepeatContext context){
  if (context instanceof RepeatContextSupport) {
    ((RepeatContextSupport)context).increment();
  }
}
