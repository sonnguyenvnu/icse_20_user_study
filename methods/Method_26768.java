/** 
 * Returns a  {@link ScannerSupplier} with all {@link BugChecker}s in Error Prone. 
 */
public static ScannerSupplier allChecks(){
  return ScannerSupplier.fromBugCheckerInfos(Iterables.concat(ENABLED_ERRORS,ENABLED_WARNINGS,DISABLED_CHECKS));
}
