private boolean actuallyUsed(List<NameOccurrence> usages){
  for (  NameOccurrence occ : usages) {
    JavaNameOccurrence jocc=(JavaNameOccurrence)occ;
    if (!jocc.isOnLeftHandSide()) {
      return true;
    }
  }
  return false;
}
