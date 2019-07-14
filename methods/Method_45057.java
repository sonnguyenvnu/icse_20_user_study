private void doEnableLinks(){
  isNavigationLinksValid=false;
  linkProvider.processLinks();
  buildSelectionToUniqueStrTreeMap();
  clearLinksCache();
  isNavigationLinksValid=true;
  textArea.setHyperlinksEnabled(true);
  warmUpWithFirstLink();
}
