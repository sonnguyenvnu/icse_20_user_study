protected void invRotateY(float angle){
  float c=cos(-angle);
  float s=sin(-angle);
  preApply(c,0,s,0,0,1,0,0,-s,0,c,0,0,0,0,1);
}
