@Override protected Traverser.Admin<Vertex> processNextStart(){
  if (!initialized)   initialize();
  return super.processNextStart();
}
