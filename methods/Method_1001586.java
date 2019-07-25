public double determinant(){
  final double ux=a.getX() - o.getX();
  final double uy=a.getY() - o.getY();
  final double vx=b.getX() - o.getX();
  final double vy=b.getY() - o.getY();
  return ux * vy - uy * vx;
}
