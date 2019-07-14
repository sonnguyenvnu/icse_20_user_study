public void end(){
  if (lineToOrigin) {
    lineToImpl(sx0,sy0,scolor0,joinToOrigin);
    lineToOrigin=false;
  }
  if (prev == LinePath.SEG_LINETO) {
    finish();
  }
  output.end();
  this.joinSegment=false;
  this.prev=LinePath.SEG_MOVETO;
}
