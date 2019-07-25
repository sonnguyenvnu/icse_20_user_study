@Override public LValue optimize() throws EvaluationException {
  final RValue optimized=super.optimize();
  if (optimized == this) {
    return this;
  }
  if (optimized instanceof Function) {
    return new LValueFunction(optimized.getPosition(),method,setter,((Function)optimized).args);
  }
  return (LValue)optimized;
}
