@Override protected boolean contains(MappeableRunContainer runContainer){
  final int runCardinality=runContainer.getCardinality();
  if (cardinality != -1) {
    if (cardinality < runCardinality) {
      return false;
    }
  }
 else {
    int card=cardinality;
    if (card < runCardinality) {
      return false;
    }
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
