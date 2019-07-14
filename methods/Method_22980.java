private LineMarker findClosestMarker(final int y){
  LineMarker closest=null;
  int closestDist=Integer.MAX_VALUE;
  for (  LineMarker m : errorPoints) {
    int dist=Math.abs(y - m.y);
    if (dist < 3 && dist < closestDist) {
      closest=m;
      closestDist=dist;
    }
  }
  return closest;
}
