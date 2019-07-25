@Override public Boolean visit(Message message){
  errors.addMessage(message);
  return true;
}
