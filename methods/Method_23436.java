public void rotateX(float angle){
  float c=cos(angle);
  float s=sin(angle);
  apply(1,0,0,0,0,c,-s,0,0,s,c,0,0,0,0,1);
}
