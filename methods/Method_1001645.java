public static List<Point2D.Double> get(Point2D.Double p1,Point2D.Double p2){
  final List<Point2D.Double> result=new ArrayList<Point2D.Double>();
  result.add(new Point2D.Double(p1.x,p2.y));
  result.add(new Point2D.Double(p2.x,p1.y));
  return result;
}
