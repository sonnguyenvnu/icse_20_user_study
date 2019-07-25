@Override public double assign(double value) throws EvaluationException {
  throw new EvaluationException(getPosition(),"Tried to assign unbound variable!");
}
