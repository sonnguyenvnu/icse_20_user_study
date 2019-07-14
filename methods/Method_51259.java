private SortedSet<DataPoint> applyMinimumValue(SortedSet<DataPoint> pointSet,double minValue){
  SortedSet<DataPoint> rc=new TreeSet<>();
  double threshold=minValue - DELTA;
  for (  DataPoint point : pointSet) {
    if (point.getScore() > threshold) {
      rc.add(point);
    }
  }
  return rc;
}
