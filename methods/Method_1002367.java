private static <T>T claim(AtomicReference<T> ref){
  T current;
  do {
    current=ref.get();
  }
 while (current == null || !ref.compareAndSet(current,null));
  return current;
}
