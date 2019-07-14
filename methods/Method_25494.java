/** 
 * Filters this  {@link ScannerSupplier} based on the provided predicate. Returns a {@link ScannerSupplier} with only the checks enabled that satisfy the predicate.
 */
@CheckReturnValue public ScannerSupplier filter(Predicate<? super BugCheckerInfo> predicate){
  ImmutableSet<String> disabled=getAllChecks().values().stream().filter(predicate.negate()).map(BugCheckerInfo::canonicalName).collect(ImmutableSet.toImmutableSet());
  return new ScannerSupplierImpl(getAllChecks(),severities(),disabled,getFlags());
}
