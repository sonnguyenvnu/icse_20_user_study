@Override public boolean dealMessage(Message message){
  smsReportMessageSet.add(message);
  System.err.println(this.getClass().getName() + "; " + message.toString());
  return true;
}
