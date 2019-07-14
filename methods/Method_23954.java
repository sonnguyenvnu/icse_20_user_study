private boolean isCCW(int x0,int y0,int x1,int y1,int x2,int y2){
  int dx0=x1 - x0;
  int dy0=y1 - y0;
  int dx1=x2 - x1;
  int dy1=y2 - y1;
  return (long)dx0 * dy1 < (long)dy0 * dx1;
}
