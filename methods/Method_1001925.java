public USegment translate(double dx,double dy){
  if (coord.length != 2) {
    throw new UnsupportedOperationException();
  }
  Point2D p1=new Point2D.Double(coord[0] + dx,coord[1] + dy);
  return new USegment(new double[]{p1.getX(),p1.getY()},pathType);
}
