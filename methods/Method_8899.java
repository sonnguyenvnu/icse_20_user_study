private void removeEntity(EntityView entityView){
  if (entityView == currentEntityView) {
    currentEntityView.deselect();
    if (editingText) {
      closeTextEnter(false);
    }
    currentEntityView=null;
    updateSettingsButton();
  }
  entitiesView.removeView(entityView);
  undoStore.unregisterUndo(entityView.getUUID());
}
