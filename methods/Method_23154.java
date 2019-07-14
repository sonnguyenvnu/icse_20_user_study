@Override public void quad(float x1,float y1,float x2,float y2,float x3,float y3,float x4,float y4){
  GeneralPath gp=new GeneralPath();
  gp.moveTo(x1,y1);
  gp.lineTo(x2,y2);
  gp.lineTo(x3,y3);
  gp.lineTo(x4,y4);
  gp.closePath();
  drawShape(gp);
}
