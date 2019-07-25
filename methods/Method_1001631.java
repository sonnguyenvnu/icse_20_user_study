public double side(Box box){
  final Point2DInt corners[]=box.getCorners();
  final double s0=side(corners[0]);
  final double s1=side(corners[1]);
  final double s2=side(corners[2]);
  final double s3=side(corners[3]);
  if (s0 > 0 && s1 > 0 && s2 > 0 && s3 > 0) {
    return 1;
  }
  if (s0 < 0 && s1 < 0 && s2 < 0 && s3 < 0) {
    return -1;
  }
  return 0;
}
