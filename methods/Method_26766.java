@SafeVarargs public static ImmutableSet<BugCheckerInfo> getSuppliers(Class<? extends BugChecker>... checkers){
  return getSuppliers(Arrays.asList(checkers));
}
