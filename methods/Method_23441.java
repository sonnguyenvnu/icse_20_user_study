public void shearX(float angle){
  float t=(float)Math.tan(angle);
  apply(1,t,0,0,0,1,0,0,0,0,1,0,0,0,0,1);
}
