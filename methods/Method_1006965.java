/** 
 * Update all the composed contexts, and also increment the parent context.
 * @see org.springframework.batch.repeat.CompletionPolicy#update(org.springframework.batch.repeat.RepeatContext)
 */
@Override public void update(RepeatContext context){
  RepeatContext[] contexts=((CompositeBatchContext)context).contexts;
  CompletionPolicy[] policies=((CompositeBatchContext)context).policies;
  for (int i=0; i < policies.length; i++) {
    policies[i].update(contexts[i]);
  }
  ((RepeatContextSupport)context).increment();
}
