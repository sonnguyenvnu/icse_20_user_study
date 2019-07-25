@Override default boolean supports(Class<? extends AlertPolicy> clazz){
  return clazz.isAssignableFrom(EmailReceiver.class);
}
