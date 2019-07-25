@Override public ISequence<T> cut(int length){
  return new PagingSequence<T>(this,PagingSequence.Page.CUT,length);
}
