private void registerRemovalUndo(final EntityView entityView){
  undoStore.registerUndo(entityView.getUUID(),() -> removeEntity(entityView));
}
