private String getUniqueStrForOffset(int offset){
  Selection selection=getSelectionForOffset(offset);
  if (selection != null) {
    String uniqueStr=selectionToUniqueStrTreeMap.get(selection);
    if (this.isLinkNavigable(uniqueStr) && this.getLinkDescription(uniqueStr) != null) {
      return uniqueStr;
    }
  }
  return null;
}
