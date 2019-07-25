@Override public void removed(Thing thing){
  if (thing instanceof Bridge) {
    removeResultsForBridge(thing.getUID());
  }
}
