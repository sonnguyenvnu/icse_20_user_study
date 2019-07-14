@Override @SuppressWarnings("NullAway") public E removeLast(){
  checkNotEmpty();
  return pollLast();
}
