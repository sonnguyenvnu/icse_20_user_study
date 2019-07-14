@Override public ImmutableSet<BugCheckerInfo> getEnabledChecks(){
  return getAllChecks().values().stream().filter(input -> !disabled.contains(input.canonicalName())).collect(ImmutableSet.toImmutableSet());
}
