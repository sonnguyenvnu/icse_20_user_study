@Override public ISequence<T> reverse(){
  return new ReversingSequence<T>(this);
}
