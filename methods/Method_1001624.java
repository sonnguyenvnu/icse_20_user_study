private boolean contains(int x,int y){
  if (points.size() <= 2) {
    return false;
  }
  if (x > maxX) {
    return false;
  }
  if (x < minX) {
    return false;
  }
  if (y > maxY) {
    return false;
  }
  if (y < minY) {
    return false;
  }
  if (isOnFrontier(new Point2DInt(x,y))) {
    return true;
  }
  int hits=0;
  int lastx=getLastPoint().getXint();
  int lasty=getLastPoint().getYint();
  int curx;
  int cury;
  for (int i=0; i < points.size(); lastx=curx, lasty=cury, i++) {
    curx=points.get(i).getXint();
    cury=points.get(i).getYint();
    if (cury == lasty) {
      continue;
    }
    final int leftx;
    if (curx < lastx) {
      if (x >= lastx) {
        continue;
      }
      leftx=curx;
    }
 else {
      if (x >= curx) {
        continue;
      }
      leftx=lastx;
    }
    final double test1;
    final double test2;
    if (cury < lasty) {
      if (y < cury || y >= lasty) {
        continue;
      }
      if (x < leftx) {
        hits++;
        continue;
      }
      test1=x - curx;
      test2=y - cury;
    }
 else {
      if (y < lasty || y >= cury) {
        continue;
      }
      if (x < leftx) {
        hits++;
        continue;
      }
      test1=x - lastx;
      test2=y - lasty;
    }
    if (test1 < test2 / (lasty - cury) * (lastx - curx)) {
      hits++;
    }
  }
  return (hits & 1) != 0;
}
