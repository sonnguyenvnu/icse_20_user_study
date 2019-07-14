@Override public boolean isEnabled(final Level level){
  return logger.isLoggable(jodd2jdk(level));
}
