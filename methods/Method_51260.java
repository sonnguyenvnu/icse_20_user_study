private SortedSet<DataPoint> applyTopScore(SortedSet<DataPoint> points,int topScore){
  SortedSet<DataPoint> s=new TreeSet<>();
  DataPoint[] arr=points.toArray(new DataPoint[]{});
  for (int i=arr.length - 1; i >= (arr.length - topScore); i--) {
    s.add(arr[i]);
  }
  return s;
}
