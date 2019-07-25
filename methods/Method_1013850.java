public void reset(int width,int height,float verticalViewAngleR){
  float halfHeight=height / 2;
  if (verticalViewAngleR == 0)   verticalViewAngleR=45;
  this.radius=(float)(halfHeight / Math.tan(Math.toRadians(verticalViewAngleR / 2.0f)));
}
