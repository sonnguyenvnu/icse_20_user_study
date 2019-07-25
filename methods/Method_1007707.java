@Override public void redo(UndoContext context) throws WorldEditException {
  checkNotNull(context.getExtent()).setBlock(position,current);
}
