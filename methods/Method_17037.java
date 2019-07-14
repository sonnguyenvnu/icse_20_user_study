@Override @SuppressWarnings("NullAway") public E getFirst(){
  checkNotEmpty();
  return peekFirst();
}
