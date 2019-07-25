@Override public void update(final double dataItem){
  throw new SketchesReadOnlyException("Call to update() on read-only buffer");
}
