private void lineToImpl(int x1,int y1,int c1,boolean joinSegment){
  computeOffset(x0,y0,x1,y1,offset);
  int mx=offset[0];
  int my=offset[1];
  if (!started) {
    emitMoveTo(x0 + mx,y0 + my,color0);
    this.sx1=x1;
    this.sy1=y1;
    this.mx0=mx;
    this.my0=my;
    started=true;
  }
 else {
    boolean ccw=isCCW(px0,py0,x0,y0,x1,y1);
    if (joinSegment) {
      if (joinStyle == LinePath.JOIN_MITER) {
        drawMiter(px0,py0,x0,y0,x1,y1,omx,omy,mx,my,color0,ccw);
      }
 else       if (joinStyle == LinePath.JOIN_ROUND) {
        drawRoundJoin(x0,y0,omx,omy,mx,my,0,color0,false,ccw,ROUND_JOIN_THRESHOLD);
      }
    }
 else {
      drawRoundJoin(x0,y0,omx,omy,mx,my,0,color0,false,ccw,ROUND_JOIN_INTERNAL_THRESHOLD);
    }
    emitLineTo(x0,y0,color0,!ccw);
  }
  emitLineTo(x0 + mx,y0 + my,color0,false);
  emitLineTo(x1 + mx,y1 + my,c1,false);
  emitLineTo(x0 - mx,y0 - my,color0,true);
  emitLineTo(x1 - mx,y1 - my,c1,true);
  this.omx=mx;
  this.omy=my;
  this.px0=x0;
  this.py0=y0;
  this.pcolor0=color0;
  this.x0=x1;
  this.y0=y1;
  this.color0=c1;
  this.prev=LinePath.SEG_LINETO;
}
