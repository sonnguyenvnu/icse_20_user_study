boolean convex(){
  if (points.size() < 4)   return true;
  boolean sign=false;
  int n=points.size();
  for (int i=0; i < n; i++) {
    double dx1=points.get((i + 2) % n).x - points.get((i + 1) % n).x;
    double dy1=points.get((i + 2) % n).y - points.get((i + 1) % n).y;
    double dx2=points.get(i).x - points.get((i + 1) % n).x;
    double dy2=points.get(i).y - points.get((i + 1) % n).y;
    double zCrossProduct=dx1 * dy2 - dy1 * dx2;
    if (i == 0)     sign=zCrossProduct > 0;
 else     if (sign != (zCrossProduct > 0))     return false;
  }
  return true;
}
