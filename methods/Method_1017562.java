@Override public boolean evaluate(TriggerContext<S,E> context){
  return ObjectUtils.nullSafeEquals(event,context.getEvent());
}
