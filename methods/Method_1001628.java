public List<Line2D> segments(){
  final List<Line2D> result=new ArrayList<Line2D>();
  Point2D cur=frame1.getMainCorner();
  for (  Point2D pt : points1) {
    result.add(new Line2D.Double(cur,pt));
    cur=pt;
  }
  result.add(new Line2D.Double(cur,frame2.getMainCorner()));
  return Collections.unmodifiableList(result);
}
