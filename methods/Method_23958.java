public void moveTo(int x0,int y0,int c0){
  if (lineToOrigin) {
    lineToImpl(sx0,sy0,scolor0,joinToOrigin);
    lineToOrigin=false;
  }
  if (prev == LinePath.SEG_LINETO) {
    finish();
  }
  this.sx0=this.x0=x0;
  this.sy0=this.y0=y0;
  this.scolor0=this.color0=c0;
  this.rindex=0;
  this.started=false;
  this.joinSegment=false;
  this.prev=LinePath.SEG_MOVETO;
}
