private boolean hasChanges(){
  return undoStore.canUndo() || entitiesView.entitiesCount() > 0;
}
