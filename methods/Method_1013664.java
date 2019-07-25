@Override public boolean supports(Class<? extends PolicyParam> clazz){
  return clazz.isAssignableFrom(EmailReceiverParam.class);
}
