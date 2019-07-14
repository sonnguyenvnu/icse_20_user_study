public static int getQuadrant(PointF p,PointF mPointCenter){
  float x=p.x;
  float y=p.y;
  if (x > mPointCenter.x) {
    if (y > mPointCenter.y) {
      return 4;
    }
 else     if (y < mPointCenter.y) {
      return 1;
    }
  }
 else   if (x < mPointCenter.x) {
    if (y > mPointCenter.y) {
      return 3;
    }
 else     if (y < mPointCenter.y) {
      return 2;
    }
  }
  return -1;
}
