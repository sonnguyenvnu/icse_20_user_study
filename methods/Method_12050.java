private boolean areAllChildrenIgnored(){
  for (  T child : getFilteredChildren()) {
    if (!isIgnored(child)) {
      return false;
    }
  }
  return true;
}
