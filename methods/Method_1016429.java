@Override public synchronized Iterator<E> keys(final boolean up){
  if (up)   return new simpleScoreIterator<E>();
  return new reverseScoreIterator<E>();
}
