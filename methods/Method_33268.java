private Point2D rotate(Point2D a,Point2D center,double angle){
  double resultX=center.getX() + (a.getX() - center.getX()) * Math.cos(angle) - (a.getY() - center.getY()) * Math.sin(angle);
  double resultY=center.getY() + (a.getX() - center.getX()) * Math.sin(angle) + (a.getY() - center.getY()) * Math.cos(angle);
  return new Point2D(resultX,resultY);
}
