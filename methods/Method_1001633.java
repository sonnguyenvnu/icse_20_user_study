public List<LineSegmentInt> segments(){
  final List<LineSegmentInt> result=new ArrayList<LineSegmentInt>();
  Point2DInt cur=getStart().getPosition();
  for (  Point2DInt intermediate : intermediates) {
    result.add(new LineSegmentInt(cur,intermediate));
    cur=intermediate;
  }
  result.add(new LineSegmentInt(cur,getEnd().getPosition()));
  return Collections.unmodifiableList(result);
}
