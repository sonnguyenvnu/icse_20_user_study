@Override public UpdateSketch rebuild(){
  if (getRetainedEntries(true) > (1 << getLgNomLongs())) {
    quickSelectAndRebuild();
  }
  return this;
}
