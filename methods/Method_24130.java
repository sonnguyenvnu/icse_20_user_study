static protected void invTranslate(PMatrix3D matrix,float tx,float ty,float tz){
  matrix.preApply(1,0,0,-tx,0,1,0,-ty,0,0,1,-tz,0,0,0,1);
}
