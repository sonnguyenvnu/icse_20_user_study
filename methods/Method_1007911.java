@Override public void log(String message,Object arg0,Object arg1){
  loggingEvent.setMessage(message);
  loggingEvent.addArgument(arg0);
  loggingEvent.addArgument(arg1);
  innerLog(loggingEvent);
}
