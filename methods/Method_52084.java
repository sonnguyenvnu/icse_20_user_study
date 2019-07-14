private void lookForViolation(Map<VariableNameDeclaration,List<NameOccurrence>> params,Object data){
  for (  Map.Entry<VariableNameDeclaration,List<NameOccurrence>> entry : params.entrySet()) {
    VariableNameDeclaration decl=entry.getKey();
    List<NameOccurrence> usages=entry.getValue();
    if (!decl.getDeclaratorId().isFormalParameter()) {
      continue;
    }
    for (    NameOccurrence occ : usages) {
      JavaNameOccurrence jocc=(JavaNameOccurrence)occ;
      if ((jocc.isOnLeftHandSide() || jocc.isSelfAssignment()) && jocc.getNameForWhichThisIsAQualifier() == null && !jocc.useThisOrSuper() && !decl.isVarargs() && (!decl.isArray() || jocc.getLocation().jjtGetParent().jjtGetParent().jjtGetNumChildren() == 1)) {
        addViolation(data,decl.getNode(),decl.getImage());
      }
    }
  }
}
