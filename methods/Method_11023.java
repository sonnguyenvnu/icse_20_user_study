private ColorCircle findNearestByPosition(float x,float y){
  ColorCircle near=null;
  double minDist=Double.MAX_VALUE;
  for (  ColorCircle colorCircle : renderer.getColorCircleList()) {
    double dist=colorCircle.sqDist(x,y);
    if (minDist > dist) {
      minDist=dist;
      near=colorCircle;
    }
  }
  return near;
}
