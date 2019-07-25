@Override protected boolean contains(MappeableRunContainer runContainer){
  if (runContainer.getCardinality() > cardinality) {
    return false;
  }
  for (int i=0; i < runContainer.numberOfRuns(); ++i) {
    int start=toIntUnsigned(runContainer.getValue(i));
    int length=toIntUnsigned(runContainer.getLength(i));
    if (!contains(start,start + length)) {
      return false;
    }
  }
  return true;
}
