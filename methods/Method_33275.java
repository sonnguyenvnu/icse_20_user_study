private Point2D rotate(final Point2D a,final Point2D center,final double angle){
  final double resultX=center.getX() + (a.getX() - center.getX()) * Math.cos(angle) - (a.getY() - center.getY()) * Math.sin(angle);
  final double resultY=center.getY() + (a.getX() - center.getX()) * Math.sin(angle) + (a.getY() - center.getY()) * Math.cos(angle);
  return new Point2D(resultX,resultY);
}
