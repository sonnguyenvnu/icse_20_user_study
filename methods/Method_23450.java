protected void invRotateZ(float angle){
  float c=cos(-angle);
  float s=sin(-angle);
  preApply(c,-s,0,0,s,c,0,0,0,0,1,0,0,0,0,1);
}
