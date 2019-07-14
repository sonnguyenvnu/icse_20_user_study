/** 
 * Returns a  {@link ScannerSupplier} built from a list of {@link BugCheckerInfo}s. 
 */
public static ScannerSupplier fromBugCheckerInfos(Iterable<BugCheckerInfo> checkers){
  ImmutableBiMap<String,BugCheckerInfo> allChecks=Streams.stream(checkers).collect(ImmutableBiMap.toImmutableBiMap(BugCheckerInfo::canonicalName,checker -> checker));
  return new ScannerSupplierImpl(allChecks,defaultSeverities(allChecks.values()),ImmutableSet.of(),ErrorProneFlags.empty());
}
