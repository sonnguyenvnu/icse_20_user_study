public void shearY(float angle){
  float t=(float)Math.tan(angle);
  apply(1,0,0,0,t,1,0,0,0,0,1,0,0,0,0,1);
}
