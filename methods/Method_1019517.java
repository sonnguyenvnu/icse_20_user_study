@Override public CompactSketch intersect(final Sketch a,final Sketch b,final boolean dstOrdered,final WritableMemory dstMem){
  reset();
  update(a);
  update(b);
  return getResult(dstOrdered,dstMem);
}
