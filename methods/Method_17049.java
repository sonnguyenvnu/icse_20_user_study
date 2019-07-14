static int ceilingPowerOfTwo(int x){
  return 1 << -Integer.numberOfLeadingZeros(x - 1);
}
