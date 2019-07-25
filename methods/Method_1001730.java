private static Line2D symetric(Line2D line){
  final double x1=line.getX1();
  final double y1=line.getY1();
  final double x2=line.getX2();
  final double y2=line.getY2();
  final double dx=x2 - x1;
  final double dy=y2 - y1;
  return new Line2D.Double(x1,y1,x1 - dx,y1 - dy);
}
