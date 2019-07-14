private int computeRoundJoin(int cx,int cy,int xa,int ya,int xb,int yb,int side,boolean flip,int[] join){
  int px, py;
  int ncoords=0;
  boolean centerSide;
  if (side == 0) {
    centerSide=side(cx,cy,xa,ya,xb,yb);
  }
 else {
    centerSide=(side == 1) ? true : false;
  }
  for (int i=0; i < numPenSegments; i++) {
    px=cx + pen_dx[i];
    py=cy + pen_dy[i];
    boolean penSide=side(px,py,xa,ya,xb,yb);
    if (penSide != centerSide) {
      penIncluded[i]=true;
    }
 else {
      penIncluded[i]=false;
    }
  }
  int start=-1, end=-1;
  for (int i=0; i < numPenSegments; i++) {
    if (penIncluded[i] && !penIncluded[(i + numPenSegments - 1) % numPenSegments]) {
      start=i;
    }
    if (penIncluded[i] && !penIncluded[(i + 1) % numPenSegments]) {
      end=i;
    }
  }
  if (end < start) {
    end+=numPenSegments;
  }
  if (start != -1 && end != -1) {
    long dxa=cx + pen_dx[start] - xa;
    long dya=cy + pen_dy[start] - ya;
    long dxb=cx + pen_dx[start] - xb;
    long dyb=cy + pen_dy[start] - yb;
    boolean rev=(dxa * dxa + dya * dya > dxb * dxb + dyb * dyb);
    int i=rev ? end : start;
    int incr=rev ? -1 : 1;
    while (true) {
      int idx=i % numPenSegments;
      px=cx + pen_dx[idx];
      py=cy + pen_dy[idx];
      join[ncoords++]=px;
      join[ncoords++]=py;
      if (i == (rev ? start : end)) {
        break;
      }
      i+=incr;
    }
  }
  return ncoords / 2;
}
