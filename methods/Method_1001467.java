public Worm move(double dx,double dy){
  final Worm result=new Worm();
  for (  Point2D pt : points) {
    result.addPoint(pt.getX() + dx,pt.getY() + dy);
  }
  return result;
}
