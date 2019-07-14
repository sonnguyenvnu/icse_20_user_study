private static long udivdi3(long u,long v){
  if (v >>> 32 == 0) {
    if (u >>> 32 < v) {
      long q0=(((u >>> 1) / v) << Long.numberOfLeadingZeros(v)) >>> 31;
      if ((u - q0 * v) >= v) {
        q0++;
      }
      return q0;
    }
 else {
      long u1=u >>> 32;
      long q1=u1 / v;
      long q0=(((u1 - q1 * v) << 32) | (u & 0xFFFF_FFFFL)) / v;
      return (q1 << 32) | q0;
    }
  }
  int n=Long.numberOfLeadingZeros(v);
  long q0=(((u >>> 1) / ((v << n) >>> 32)) << n) >>> 31;
  if (q0 != 0) {
    q0--;
  }
  if (Long.compareUnsigned((u - q0 * v),v) >= 0) {
    q0++;
  }
  return q0;
}
