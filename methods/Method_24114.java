protected void flushPixels(){
  drawPixels(mx1,my1,mx2 - mx1,my2 - my1);
  modified=false;
}
