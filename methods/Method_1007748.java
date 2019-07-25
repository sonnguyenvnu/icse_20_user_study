public static double inv(RValue x) throws EvaluationException {
  return ~(long)x.getValue();
}
