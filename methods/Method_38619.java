@Override public JoyScanner setIgnoreExceptions(final boolean ignoreExceptions){
  requireNotStarted(classScanner);
  this.ignoreExceptions=ignoreExceptions;
  return this;
}
