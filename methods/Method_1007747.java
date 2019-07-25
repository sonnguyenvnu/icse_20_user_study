public static double not(RValue x) throws EvaluationException {
  return x.getValue() > 0.0 ? 0.0 : 1.0;
}
