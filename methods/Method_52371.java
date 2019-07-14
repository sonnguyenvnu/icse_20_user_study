protected boolean assigned(List<NameOccurrence> usages){
  for (  NameOccurrence occ : usages) {
    JavaNameOccurrence jocc=(JavaNameOccurrence)occ;
    if (jocc.isOnLeftHandSide() || jocc.isSelfAssignment()) {
      return true;
    }
  }
  return false;
}
