@Override public RValue optimize() throws EvaluationException {
  return new SimpleFor(getPosition(),counter.optimize(),first.optimize(),last.optimize(),body.optimize());
}
