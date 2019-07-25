/** 
 * Registers objects using the annotation based listener configuration.
 * @param listener the object that has a method configured with listener annotation
 * @return this for fluent chaining
 */
@Override @SuppressWarnings("unchecked") public SimpleStepBuilder<I,O> listener(Object listener){
  super.listener(listener);
  Set<Method> skipListenerMethods=new HashSet<>();
  skipListenerMethods.addAll(ReflectionUtils.findMethod(listener.getClass(),OnSkipInRead.class));
  skipListenerMethods.addAll(ReflectionUtils.findMethod(listener.getClass(),OnSkipInProcess.class));
  skipListenerMethods.addAll(ReflectionUtils.findMethod(listener.getClass(),OnSkipInWrite.class));
  if (skipListenerMethods.size() > 0) {
    StepListenerFactoryBean factory=new StepListenerFactoryBean();
    factory.setDelegate(listener);
    skipListeners.add((SkipListener)factory.getObject());
  }
  @SuppressWarnings("unchecked") SimpleStepBuilder<I,O> result=this;
  return result;
}
