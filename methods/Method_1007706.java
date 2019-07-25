@Override public void undo(UndoContext context) throws WorldEditException {
  checkNotNull(context.getExtent()).setBlock(position,previous);
}
