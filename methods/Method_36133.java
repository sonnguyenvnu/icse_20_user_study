@Override public Notifier notifier(){
  return new ConsoleNotifier(verboseLoggingEnabled());
}
