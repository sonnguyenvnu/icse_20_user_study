private boolean isLocallyNavigable(String uniqueStr){
  return linkProvider.getDefinitionToSelectionMap().keySet().contains(uniqueStr);
}
