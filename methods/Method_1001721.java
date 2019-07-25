private Point2D mirror(Point2D center,Point2D pt){
  final double x=2 * center.getX() - pt.getX();
  final double y=2 * center.getY() - pt.getY();
  return new Point2D.Double(x,y);
}
