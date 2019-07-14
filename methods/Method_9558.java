private boolean hasChanges(){
  if (initialRulesType != currentType) {
    return true;
  }
  if (initialMinus.size() != currentMinus.size()) {
    return true;
  }
  if (initialPlus.size() != currentPlus.size()) {
    return true;
  }
  Collections.sort(initialPlus);
  Collections.sort(currentPlus);
  if (!initialPlus.equals(currentPlus)) {
    return true;
  }
  Collections.sort(initialMinus);
  Collections.sort(currentMinus);
  if (!initialMinus.equals(currentMinus)) {
    return true;
  }
  return false;
}
