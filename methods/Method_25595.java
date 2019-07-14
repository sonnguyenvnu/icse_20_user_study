public void reportMatch(Description description){
  SeverityLevel override=severityMap.get(description.checkName);
  if (override != null) {
    description=description.applySeverityOverride(override);
  }
  statisticsCollector.incrementCounter(statsKey(description.checkName + "-findings"));
  descriptionListener.onDescribed(description);
}
