public static double fac(RValue x) throws EvaluationException {
  final int n=(int)x.getValue();
  if (n < 0) {
    return 0;
  }
  if (n >= factorials.length) {
    return Double.POSITIVE_INFINITY;
  }
  return factorials[n];
}
