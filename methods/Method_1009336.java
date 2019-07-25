public Errors merge(Collection<ErrorMessage> errorMessages){
  for (  ErrorMessage message : errorMessages)   addMessage(message);
  return this;
}
