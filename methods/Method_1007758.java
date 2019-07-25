public static double inc(LValue x) throws EvaluationException {
  return x.assign(x.getValue() + 1);
}
