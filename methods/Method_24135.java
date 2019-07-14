static protected void invScale(PMatrix3D matrix,float x,float y,float z){
  matrix.preApply(1 / x,0,0,0,0,1 / y,0,0,0,0,1 / z,0,0,0,0,1);
}
