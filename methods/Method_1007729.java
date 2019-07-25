@Override public LValue optimize() throws EvaluationException {
  throw new EvaluationException(getPosition(),"Tried to optimize unbound variable!");
}
