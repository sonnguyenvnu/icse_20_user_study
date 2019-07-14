@Override @SuppressWarnings("NullAway") public E removeFirst(){
  checkNotEmpty();
  return pollFirst();
}
