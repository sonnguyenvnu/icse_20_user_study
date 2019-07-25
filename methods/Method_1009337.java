public Errors merge(Errors errors){
  for (  ErrorMessage message : errors.getMessages())   addMessage(message);
  return this;
}
