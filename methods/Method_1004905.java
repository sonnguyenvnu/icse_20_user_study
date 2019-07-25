@Override protected void lock(){
  super.lock();
  if (null != globalElements) {
    globalElements=Collections.unmodifiableList(globalElements);
  }
  if (null != globalEntities) {
    globalEntities=Collections.unmodifiableList(globalEntities);
  }
  if (null != globalEdges) {
    globalEdges=Collections.unmodifiableList(globalEdges);
  }
}
