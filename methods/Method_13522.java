@Override public boolean dealMessage(Message message){
  System.err.println(this.getClass().getName() + "; " + message.toString());
  return true;
}
