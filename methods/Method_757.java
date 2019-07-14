public static int stringSize(long x){
  long p=10;
  for (int i=1; i < 19; i++) {
    if (x < p)     return i;
    p=10 * p;
  }
  return 19;
}
