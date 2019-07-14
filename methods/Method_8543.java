public void setActualRect(float aspectRatio){
  calculateRect(actualRect,aspectRatio);
  updateTouchAreas();
  invalidate();
}
