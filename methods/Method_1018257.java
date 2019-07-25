/** 
 * Returns the greatest common divisor of  {@code a, b}. Returns  {@code 0} if{@code a == 0 && b == 0}.
 * @throws IllegalArgumentException if {@code a < 0} or {@code b < 0}
 */
private static int gcd(int a,int b){
  if (a < 0) {
    throw new IllegalArgumentException("a (" + a + ") must be >= 0");
  }
  if (b < 0) {
    throw new IllegalArgumentException("b (" + b + ") must be >= 0");
  }
  if (a == 0) {
    return b;
  }
 else   if (b == 0) {
    return a;
  }
  int aTwos=Integer.numberOfTrailingZeros(a);
  a>>=aTwos;
  int bTwos=Integer.numberOfTrailingZeros(b);
  b>>=bTwos;
  while (a != b) {
    int delta=a - b;
    int minDeltaOrZero=delta & (delta >> (Integer.SIZE - 1));
    a=delta - minDeltaOrZero - minDeltaOrZero;
    b+=minDeltaOrZero;
    a>>=Integer.numberOfTrailingZeros(a);
  }
  return a << Math.min(aTwos,bTwos);
}
