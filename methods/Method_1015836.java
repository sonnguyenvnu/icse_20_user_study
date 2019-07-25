@Override public void log(LogRecord record){
  dispatcher.queue(record);
}
