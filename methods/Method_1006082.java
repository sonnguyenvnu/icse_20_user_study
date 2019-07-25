/** 
 * The log event will be forwarded to the  {@link LogMessages} archive.
 */
@Override public void append(LogEvent event){
  MutableLogEvent copy=new MutableLogEvent();
  copy.initFrom(event);
  DefaultTaskExecutor.runInJavaFXThread(() -> LogMessages.getInstance().add(copy));
}
