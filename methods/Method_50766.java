@Override public Object visit(ASTUserClass node,Object data){
  if (Helper.isTestMethodOrClass(node) || Helper.isSystemLevelClass(node)) {
    return data;
  }
  if (!Helper.isTestMethodOrClass(node)) {
    boolean sharingFound=isSharingPresent(node);
    checkForSharingDeclaration(node,data,sharingFound);
    checkForDatabaseMethods(node,data,sharingFound);
  }
  localCacheOfReportedNodes.clear();
  return data;
}
