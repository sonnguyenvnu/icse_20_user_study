/** 
 * Checks if target is not defined yet.
 */
private void assertTargetIsNotDefined(){
  if (targetInputStream != null) {
    throw new ProxettaException("Target already defined");
  }
}
