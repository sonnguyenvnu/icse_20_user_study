@Override public UpdateSketch rebuild(){
  if (isDirty()) {
    rebuildDirty();
  }
  return this;
}
