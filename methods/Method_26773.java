private static ImmutableSet<String> enabledCheckNames(){
  return StreamSupport.stream(Iterables.concat(ENABLED_ERRORS,ENABLED_WARNINGS).spliterator(),false).map(BugCheckerInfo::canonicalName).collect(toImmutableSet());
}
