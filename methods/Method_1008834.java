static <E>Deque<E> deque(Deque<E> deque,@Nullable Object mutex){
  return new SynchronizedDeque<E>(deque,mutex);
}
