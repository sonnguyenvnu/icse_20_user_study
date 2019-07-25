@Override public Iterator<U> iterator(){
  return new LimitedCardinalitySequence.LimitedCardinalityIterator();
}
