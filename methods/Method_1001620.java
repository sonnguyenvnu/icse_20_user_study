private static double direction(Point2D origin,Point2D point1,Point2D point2){
  return determinant(point2.getX() - origin.getX(),point2.getY() - origin.getY(),point1.getX() - origin.getX(),point1.getY() - origin.getY());
}
