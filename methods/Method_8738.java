public void setTouchCoords(float x,float y){
  touchX=x;
  touchY=y;
  calcRadius();
  invalidateSelf();
}
