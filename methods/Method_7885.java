private void checkMinMax(boolean zoom){
  float moveToX=translationX;
  float moveToY=translationY;
  updateMinMax(scale);
  if (translationX < minX) {
    moveToX=minX;
  }
 else   if (translationX > maxX) {
    moveToX=maxX;
  }
  if (translationY < minY) {
    moveToY=minY;
  }
 else   if (translationY > maxY) {
    moveToY=maxY;
  }
  animateTo(scale,moveToX,moveToY,zoom);
}
