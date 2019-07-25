public static double shl(RValue lhs,RValue rhs) throws EvaluationException {
  return (long)lhs.getValue() << (long)rhs.getValue();
}
