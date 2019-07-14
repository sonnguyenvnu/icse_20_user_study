public int divide(int a,int b) throws ArithmeticException {
  if (b == 0) {
    throw new java.lang.ArithmeticException("ERROR");
  }
  int absa=abs(a);
  int absb=abs(b);
  int product=0;
  int x=0;
  while (product + absb <= absa) {
    product+=absb;
    x++;
  }
  if ((a < 0 && b < 0) || (a > 0 && b > 0)) {
    return x;
  }
 else {
    return negate(x);
  }
}
