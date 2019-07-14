@Override public void triangle(float x1,float y1,float x2,float y2,float x3,float y3){
  gpath=new GeneralPath();
  gpath.moveTo(x1,y1);
  gpath.lineTo(x2,y2);
  gpath.lineTo(x3,y3);
  gpath.closePath();
  drawShape(gpath);
}
