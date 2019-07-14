public static ImmutableSet<BugCheckerInfo> getSuppliers(Iterable<Class<? extends BugChecker>> checkers){
  return Streams.stream(checkers).map(BugCheckerInfo::create).collect(ImmutableSet.toImmutableSet());
}
