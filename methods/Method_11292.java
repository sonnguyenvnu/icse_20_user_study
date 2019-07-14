private void zoom(float zoom){
  float z=zoom / getMatrixScaleX();
  matrix.postScale(z,z,scaleX,scaleY);
  invalidate();
}
