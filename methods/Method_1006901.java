/** 
 * @see org.springframework.batch.repeat.CompletionPolicy#update(org.springframework.batch.repeat.RepeatContext)
 */
@Override public void update(RepeatContext context){
  Assert.state(delegate != null,"The delegate resource has not been initialised. " + "Remember to register this object as a StepListener.");
  delegate.update(context);
}
