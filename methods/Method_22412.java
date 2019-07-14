protected Mode findMode(String id){
  for (  Mode mode : getModeList()) {
    if (mode.getIdentifier().equals(id)) {
      return mode;
    }
  }
  return null;
}
