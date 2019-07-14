private static int log2Floor(int n){
  return n == 0 ? -1 : 31 ^ Integer.numberOfLeadingZeros(n);
}
