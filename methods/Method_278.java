public boolean requiresLocal(){
  if (isBoundToRoot()) {
    return false;
  }
  if (isSingleFieldBinding()) {
    return false;
  }
  return true;
}
