public static void setRectToRect(Matrix matrix,RectF src,RectF dst,int rotation,boolean translate){
  float tx, sx;
  float ty, sy;
  boolean xLarger=false;
  if (rotation == 90 || rotation == 270) {
    sx=dst.height() / src.width();
    sy=dst.width() / src.height();
  }
 else {
    sx=dst.width() / src.width();
    sy=dst.height() / src.height();
  }
  if (sx < sy) {
    sx=sy;
    xLarger=true;
  }
 else {
    sy=sx;
  }
  if (translate) {
    matrix.setTranslate(dst.left,dst.top);
  }
  if (rotation == 90) {
    matrix.preRotate(90);
    matrix.preTranslate(0,-dst.width());
  }
 else   if (rotation == 180) {
    matrix.preRotate(180);
    matrix.preTranslate(-dst.width(),-dst.height());
  }
 else   if (rotation == 270) {
    matrix.preRotate(270);
    matrix.preTranslate(-dst.height(),0);
  }
  if (translate) {
    tx=-src.left * sx;
    ty=-src.top * sy;
  }
 else {
    tx=dst.left - src.left * sx;
    ty=dst.top - src.top * sy;
  }
  float diff;
  if (xLarger) {
    diff=dst.width() - src.width() * sy;
  }
 else {
    diff=dst.height() - src.height() * sy;
  }
  diff=diff / 2.0f;
  if (xLarger) {
    tx+=diff;
  }
 else {
    ty+=diff;
  }
  matrix.preScale(sx,sy);
  if (translate) {
    matrix.preTranslate(tx,ty);
  }
}
