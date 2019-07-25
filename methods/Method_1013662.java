public boolean supports(Class<? extends AlertPolicy> clazz){
  return clazz.isAssignableFrom(AbstractEmailReceiver.class);
}
