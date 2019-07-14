static int ceilingNextPowerOfTwo(int x){
  return 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(x - 1));
}
