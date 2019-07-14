/** 
 * Returns a  {@link ScannerSupplier} with the {@link BugChecker}s that are in the ENABLED_ERRORS list.
 */
public static ScannerSupplier errorChecks(){
  return allChecks().filter(Predicates.in(ENABLED_ERRORS));
}
