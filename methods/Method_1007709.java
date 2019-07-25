@Override public void undo(UndoContext context) throws WorldEditException {
  entity=checkNotNull(context.getExtent()).createEntity(location,state);
}
