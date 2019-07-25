@Override public void redo(UndoContext context) throws WorldEditException {
  checkNotNull(context.getExtent()).setBiome(position,current);
}
