@Override public RValue optimize() throws EvaluationException {
  final RValue newCondition=condition.optimize();
  if (newCondition instanceof Constant && newCondition.getValue() <= 0) {
    if (footChecked) {
      return body.optimize();
    }
 else {
      return new Constant(getPosition(),0.0);
    }
  }
  return new While(getPosition(),newCondition,body.optimize(),footChecked);
}
