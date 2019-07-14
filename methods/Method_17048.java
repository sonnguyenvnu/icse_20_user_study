@Override protected Buffer<E> create(E e){
  return new RingBuffer<>(e);
}
