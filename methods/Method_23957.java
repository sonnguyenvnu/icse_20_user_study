private void drawRoundJoin(int x,int y,int omx,int omy,int mx,int my,int side,int color,boolean flip,boolean rev,long threshold){
  if ((omx == 0 && omy == 0) || (mx == 0 && my == 0)) {
    return;
  }
  long domx=(long)omx - mx;
  long domy=(long)omy - my;
  long len=domx * domx + domy * domy;
  if (len < threshold) {
    return;
  }
  if (rev) {
    omx=-omx;
    omy=-omy;
    mx=-mx;
    my=-my;
  }
  int bx0=x + omx;
  int by0=y + omy;
  int bx1=x + mx;
  int by1=y + my;
  int npoints=computeRoundJoin(x,y,bx0,by0,bx1,by1,side,flip,join);
  for (int i=0; i < npoints; i++) {
    emitLineTo(join[2 * i],join[2 * i + 1],color,rev);
  }
}
