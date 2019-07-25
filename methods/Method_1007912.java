@Override public void log(String message,Object... args){
  loggingEvent.setMessage(message);
  loggingEvent.addArguments(args);
  innerLog(loggingEvent);
}
