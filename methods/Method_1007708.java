@Override public void redo(UndoContext context) throws WorldEditException {
  entity=checkNotNull(context.getExtent()).createEntity(location,state);
}
