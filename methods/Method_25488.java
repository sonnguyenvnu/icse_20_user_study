private static ImmutableMap<String,SeverityLevel> defaultSeverities(Iterable<BugCheckerInfo> checkers){
  ImmutableMap.Builder<String,SeverityLevel> severities=ImmutableMap.builder();
  for (  BugCheckerInfo check : checkers) {
    severities.put(check.canonicalName(),check.defaultSeverity());
  }
  return severities.build();
}
