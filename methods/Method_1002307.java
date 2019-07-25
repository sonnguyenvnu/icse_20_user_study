static <E>AtomicReferenceArray<E> allocate(int capacity){
  return new AtomicReferenceArray<E>(capacity);
}
