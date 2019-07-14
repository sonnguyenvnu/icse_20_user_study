public Rect clipSquare(Rect rect){
  int w=rect.width();
  int h=rect.height();
  int min=Math.min(w,h);
  int cx=rect.centerX();
  int cy=rect.centerY();
  int r=min / 2;
  return new Rect(cx - r,cy - r,cx + r,cy + r);
}
