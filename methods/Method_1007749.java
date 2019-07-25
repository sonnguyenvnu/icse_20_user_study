public static double lth(RValue lhs,RValue rhs) throws EvaluationException {
  return lhs.getValue() < rhs.getValue() ? 1.0 : 0.0;
}
