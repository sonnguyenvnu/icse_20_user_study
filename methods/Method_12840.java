public void resetPolyMatrix(int pointCount){
  mPolyMatrix.reset();
  mPolyMatrix.setPolyToPoly(src,0,dst,0,pointCount);
}
