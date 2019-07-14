protected static float interpolate(float fraction,float startValue,float endValue){
  float diff=endValue - startValue;
  return startValue + (diff * fraction);
}
