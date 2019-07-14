@Override public boolean isEnabled(final Logger.Level level){
  return logger.isEnabled(jodd2log4j2(level));
}
