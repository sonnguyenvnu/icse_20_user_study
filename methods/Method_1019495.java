@Override public void update(final Memory mem){
  throw new SketchesReadOnlyException("Call to update() on read-only Union");
}
