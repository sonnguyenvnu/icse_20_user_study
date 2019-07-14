public static float unlerp(float start,float end,float value){
  float domainSize=end - start;
  if (domainSize == 0) {
    throw new IllegalArgumentException("Can't reverse interpolate with domain size of 0");
  }
  return (value - start) / domainSize;
}
