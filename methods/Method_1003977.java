@Override public void reset() throws SecurityException {
  if (Boolean.parseBoolean(System.getProperty(LOGGING_MANAGER_IGNORERESET,"true"))) {
    System.err.println("UnresettableLogManager is ignoring a reset() request.");
  }
 else {
    super.reset();
  }
}
