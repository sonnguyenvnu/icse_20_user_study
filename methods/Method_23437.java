public void rotateY(float angle){
  float c=cos(angle);
  float s=sin(angle);
  apply(c,0,s,0,0,1,0,0,-s,0,c,0,0,0,0,1);
}
