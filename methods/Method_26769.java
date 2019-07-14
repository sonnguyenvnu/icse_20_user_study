/** 
 * Returns a  {@link ScannerSupplier} with the {@link BugChecker}s that are in the ENABLED lists.
 */
public static ScannerSupplier defaultChecks(){
  return allChecks().filter(Predicates.or(Predicates.in(ENABLED_ERRORS),Predicates.in(ENABLED_WARNINGS)));
}
