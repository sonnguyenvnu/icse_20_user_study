@Override public A next(){
  if (!readyToReverse())   prepareForReversal();
  return reversingIterator.previous();
}
