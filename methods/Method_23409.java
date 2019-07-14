/** 
 * Check to see if two rectangles intersect one another
 */
private boolean intersect(int sx1,int sy1,int sx2,int sy2,int dx1,int dy1,int dx2,int dy2){
  int sw=sx2 - sx1 + 1;
  int sh=sy2 - sy1 + 1;
  int dw=dx2 - dx1 + 1;
  int dh=dy2 - dy1 + 1;
  if (dx1 < sx1) {
    dw+=dx1 - sx1;
    if (dw > sw) {
      dw=sw;
    }
  }
 else {
    int w=sw + sx1 - dx1;
    if (dw > w) {
      dw=w;
    }
  }
  if (dy1 < sy1) {
    dh+=dy1 - sy1;
    if (dh > sh) {
      dh=sh;
    }
  }
 else {
    int h=sh + sy1 - dy1;
    if (dh > h) {
      dh=h;
    }
  }
  return !(dw <= 0 || dh <= 0);
}
