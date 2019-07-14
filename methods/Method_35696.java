@Override public void error(String message,Throwable t){
  err.println(formatMessage(message));
  t.printStackTrace(err);
}
