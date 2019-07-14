@Override public boolean hasTxContext(){
  return TracingContext.tracing().hasGroup() && txContext(TracingContext.tracing().groupId()) != null;
}
