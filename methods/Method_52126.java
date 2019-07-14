private boolean actuallyUsed(List<NameOccurrence> usages){
  for (  NameOccurrence nameOccurrence : usages) {
    JavaNameOccurrence jNameOccurrence=(JavaNameOccurrence)nameOccurrence;
    if (!jNameOccurrence.isOnLeftHandSide()) {
      return true;
    }
  }
  return false;
}
