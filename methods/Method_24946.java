public void setPos(int pos){
  this.pos=pos;
  lPolyX=0;
  rPolyX=0;
  if (pos > 0) {
    rPolyX=pos;
  }
 else   if (pos < 0) {
    lPolyX=pos;
  }
}
