private float getChange(){
  int pixels=xCurrent - xLast;
  return pixels * incValue;
}
