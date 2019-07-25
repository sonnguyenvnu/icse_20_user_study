@Override public void undo(UndoContext context) throws WorldEditException {
  checkNotNull(context.getExtent()).setBiome(position,previous);
}
