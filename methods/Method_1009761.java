public Timber.Tree tree(){
  return new Timber.DebugTree(){
    @Override protected void log(    int priority,    String tag,    String message,    Throwable t){
      addEntry(new LogEntry(priority,tag,message,LOG_DATE_PATTERN.format(Calendar.getInstance().getTime())));
    }
  }
;
}
