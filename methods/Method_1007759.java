public static double dec(LValue x) throws EvaluationException {
  return x.assign(x.getValue() - 1);
}
