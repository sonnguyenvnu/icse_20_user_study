public void lineTo(int x1,int y1,int c1){
  if (lineToOrigin) {
    if (x1 == sx0 && y1 == sy0) {
      return;
    }
    lineToImpl(sx0,sy0,scolor0,joinToOrigin);
    lineToOrigin=false;
  }
 else   if (x1 == x0 && y1 == y0) {
    return;
  }
 else   if (x1 == sx0 && y1 == sy0) {
    lineToOrigin=true;
    joinToOrigin=joinSegment;
    joinSegment=false;
    return;
  }
  lineToImpl(x1,y1,c1,joinSegment);
  joinSegment=false;
}
