public static float unlerp(int start,int end,int value){
  int domainSize=end - start;
  if (domainSize == 0) {
    throw new IllegalArgumentException("Can't reverse interpolate with domain size of 0");
  }
  return (float)(value - start) / domainSize;
}
