@Override public JoyScanner setIncludedEntries(final String... includedEntries){
  requireNotStarted(classScanner);
  Collections.addAll(this.includedEntries,includedEntries);
  return this;
}
