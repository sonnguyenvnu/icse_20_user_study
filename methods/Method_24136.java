static protected void invScale(PMatrix2D matrix,float x,float y){
  matrix.preApply(1 / x,0,0,1 / y,0,0);
}
