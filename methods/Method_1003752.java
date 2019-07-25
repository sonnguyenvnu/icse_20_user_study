@Override public TransformablePublisher<T> publisher(){
  Iterator<? extends Promise<T>> iterator=promises.iterator();
  return Streams.flatYield(r -> {
    if (iterator.hasNext()) {
      return iterator.next();
    }
 else {
      return Promise.ofNull();
    }
  }
);
}
