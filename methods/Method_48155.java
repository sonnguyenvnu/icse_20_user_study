public KCVSLogManager getKCVSLogManager(String logName){
  Preconditions.checkArgument(configuration.restrictTo(logName).get(LOG_BACKEND).equalsIgnoreCase(LOG_BACKEND.getDefaultValue()));
  return (KCVSLogManager)getLogManager(logName);
}
