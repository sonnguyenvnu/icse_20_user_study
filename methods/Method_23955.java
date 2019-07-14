private boolean side(int x,int y,int x0,int y0,int x1,int y1){
  long lx=x;
  long ly=y;
  long lx0=x0;
  long ly0=y0;
  long lx1=x1;
  long ly1=y1;
  return (ly0 - ly1) * lx + (lx1 - lx0) * ly + (lx0 * ly1 - lx1 * ly0) > 0;
}
