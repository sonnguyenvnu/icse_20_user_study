private ColorCircle findNearestByColor(int color){
  float[] hsv=new float[3];
  Color.colorToHSV(color,hsv);
  ColorCircle near=null;
  double minDiff=Double.MAX_VALUE;
  double x=hsv[1] * Math.cos(hsv[0] * Math.PI / 180);
  double y=hsv[1] * Math.sin(hsv[0] * Math.PI / 180);
  for (  ColorCircle colorCircle : renderer.getColorCircleList()) {
    float[] hsv1=colorCircle.getHsv();
    double x1=hsv1[1] * Math.cos(hsv1[0] * Math.PI / 180);
    double y1=hsv1[1] * Math.sin(hsv1[0] * Math.PI / 180);
    double dx=x - x1;
    double dy=y - y1;
    double dist=dx * dx + dy * dy;
    if (dist < minDiff) {
      minDiff=dist;
      near=colorCircle;
    }
  }
  return near;
}
