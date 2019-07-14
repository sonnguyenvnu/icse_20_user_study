/** 
 * Composes this  {@link ScannerSupplier} with the {@code other} {@link ScannerSupplier}. The set of checks that are turned on is the intersection of the checks on in  {@code this} and {@code other}.
 */
@CheckReturnValue public ScannerSupplier plus(ScannerSupplier other){
  HashBiMap<String,BugCheckerInfo> combinedAllChecks=HashBiMap.create(this.getAllChecks());
  other.getAllChecks().forEach((k,v) -> {
    BugCheckerInfo existing=combinedAllChecks.putIfAbsent(k,v);
    if (existing != null && !existing.checkerClass().getName().contentEquals(v.checkerClass().getName())) {
      throw new IllegalArgumentException(String.format("Cannot combine scanner suppliers with different implementations of" + " '%s': %s, %s",k,v.checkerClass().getName(),existing.checkerClass().getName()));
    }
  }
);
  HashMap<String,SeverityLevel> combinedSeverities=new LinkedHashMap<>(this.severities());
  other.severities().forEach((k,v) -> {
    SeverityLevel existing=combinedSeverities.putIfAbsent(k,v);
    if (existing != null && !existing.equals(v)) {
      throw new IllegalArgumentException(String.format("Cannot combine scanner suppliers with different severities for" + " '%s': %s, %s",k,v,existing));
    }
  }
);
  ImmutableSet<String> disabled=Sets.difference(combinedAllChecks.keySet(),Sets.union(enabled(),other.enabled())).immutableCopy();
  ErrorProneFlags combinedFlags=this.getFlags().plus(other.getFlags());
  return new ScannerSupplierImpl(ImmutableBiMap.copyOf(combinedAllChecks),ImmutableMap.copyOf(combinedSeverities),disabled,combinedFlags);
}
