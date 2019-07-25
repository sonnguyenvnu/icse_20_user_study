private static double[] range(Coordinate[] points,int offset,int length){
  double minX=points[0].x;
  double maxX=points[0].x;
  double minY=points[0].y;
  double maxY=points[0].y;
  for (int i=1; i < length; ++i) {
    if (points[offset + i].x < minX) {
      minX=points[offset + i].x;
    }
    if (points[offset + i].x > maxX) {
      maxX=points[offset + i].x;
    }
    if (points[offset + i].y < minY) {
      minY=points[offset + i].y;
    }
    if (points[offset + i].y > maxY) {
      maxY=points[offset + i].y;
    }
  }
  return new double[]{minX,maxX,minY,maxY};
}
