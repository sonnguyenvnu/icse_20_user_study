protected boolean isMatch(String invokerId){
  if (allEffective) {
    return true;
  }
 else {
    if (excludeId.size() == 0) {
      return effectiveId.contains(invokerId);
    }
 else {
      return !excludeId.contains(invokerId);
    }
  }
}
