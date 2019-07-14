protected static int nextPowerOfTwo(int val){
  int ret=1;
  while (ret < val)   ret<<=1;
  return ret;
}
