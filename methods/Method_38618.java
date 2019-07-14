@Override public JoyScanner setExcludedJars(final String... excludedJars){
  requireNotStarted(classScanner);
  Collections.addAll(this.excludedJars,excludedJars);
  return this;
}
