@Override public void log(String message){
  loggingEvent.setMessage(message);
  innerLog(loggingEvent);
}
