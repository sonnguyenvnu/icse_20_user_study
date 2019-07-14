private Point2D makeControlPoint(final double endX,final double endY,final Circle circle,final int numSegments,int direction){
  final double controlPointDistance=(4.0 / 3.0) * Math.tan(Math.PI / (2 * numSegments)) * circle.getRadius();
  final Point2D center=new Point2D(circle.getCenterX(),circle.getCenterY());
  final Point2D end=new Point2D(endX,endY);
  Point2D perp=rotate(center,end,direction * Math.PI / 2.);
  Point2D diff=perp.subtract(end);
  diff=diff.normalize();
  diff=scale(diff,controlPointDistance);
  return end.add(diff);
}
