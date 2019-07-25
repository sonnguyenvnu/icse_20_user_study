@Override public void log(String message,Object arg){
  loggingEvent.setMessage(message);
  loggingEvent.addArgument(arg);
  innerLog(loggingEvent);
}
