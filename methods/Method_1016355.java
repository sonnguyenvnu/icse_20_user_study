public static long max(final int len){
  long c=0;
  for (int i=0; i < len; i++)   c=(c << 6) | 63;
  return c;
}
