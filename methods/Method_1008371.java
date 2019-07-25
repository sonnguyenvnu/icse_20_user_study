@Override public DocIdSetIterator iterator(){
  return TwoPhaseIterator.asDocIdSetIterator(twoPhaseIterator());
}
