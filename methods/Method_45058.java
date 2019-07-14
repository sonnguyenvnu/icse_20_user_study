private void warmUpWithFirstLink(){
  if (selectionToUniqueStrTreeMap.keySet().size() > 0) {
    Selection selection=selectionToUniqueStrTreeMap.keySet().iterator().next();
    getLinkDescriptionForOffset(selection.from);
  }
}
