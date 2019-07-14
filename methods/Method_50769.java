/** 
 * Check if class has no sharing declared
 * @param node
 * @param data
 */
private void checkForSharingDeclaration(ApexNode<?> node,Object data,boolean sharingFound){
  final boolean foundAnyDMLorSOQL=Helper.foundAnyDML(node) || Helper.foundAnySOQLorSOSL(node);
  if (!sharingFound && !Helper.isTestMethodOrClass(node) && foundAnyDMLorSOQL) {
    reportViolation(node,data);
  }
}
