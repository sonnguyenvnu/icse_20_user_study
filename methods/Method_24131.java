static protected void invTranslate(PMatrix2D matrix,float tx,float ty){
  matrix.preApply(1,0,-tx,0,1,-ty);
}
