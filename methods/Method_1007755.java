public static double and(RValue lhs,RValue rhs) throws EvaluationException {
  return lhs.getValue() > 0.0 && rhs.getValue() > 0.0 ? 1.0 : 0.0;
}
