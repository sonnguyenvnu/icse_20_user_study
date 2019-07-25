public static double shr(RValue lhs,RValue rhs) throws EvaluationException {
  return (long)lhs.getValue() >> (long)rhs.getValue();
}
