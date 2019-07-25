@Override public boolean validate() throws ConfigurationException {
  return super.validate() && myDuplicateModuleNames.isEmpty();
}
