@Override public void append(LogEvent event){
  MessageManager messageManager=Cat.getManager();
  boolean isTraceMode=false;
  if (messageManager != null) {
    isTraceMode=messageManager.isTraceMode();
  }
  Level level=event.getLevel();
  if (level.isMoreSpecificThan(Level.ERROR)) {
    logError(event);
  }
 else   if (isTraceMode) {
    logTrace(event);
  }
}
