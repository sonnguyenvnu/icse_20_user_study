static private Point2D rnd(Random rnd,Point2D pt,double v){
  final double x=pt.getX() + v * rnd.nextDouble();
  final double y=pt.getY() + v * rnd.nextDouble();
  return new Point2D.Double(x,y);
}
