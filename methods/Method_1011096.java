@Override public ISequence<T> concat(ISequence<? extends T> that){
  if (USE_NULL_SEQUENCE) {
    if (that == null) {
      return this;
    }
  }
  return new ConcatingSequence<T>(this,that);
}
