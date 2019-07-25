@Override public void reset(){
  throw new SketchesReadOnlyException("Call to reset() on read-only buffer");
}
