private boolean actuallyUsed(VariableNameDeclaration nameDecl,List<NameOccurrence> usages){
  for (  NameOccurrence occ : usages) {
    JavaNameOccurrence jocc=(JavaNameOccurrence)occ;
    if (jocc.isOnLeftHandSide()) {
      if (nameDecl.isArray() && jocc.getLocation().jjtGetParent().jjtGetParent().jjtGetNumChildren() > 1) {
        return true;
      }
      continue;
    }
 else {
      return true;
    }
  }
  return false;
}
