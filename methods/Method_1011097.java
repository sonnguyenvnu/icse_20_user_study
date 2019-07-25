@Override public ISequence<T> subtract(ISequence<?> that){
  if (USE_NULL_SEQUENCE) {
    if (that == null) {
      return this;
    }
  }
  return new ComparingSequence<T>(ComparingSequence.Kind.SUBSTRACTION,this,that);
}
