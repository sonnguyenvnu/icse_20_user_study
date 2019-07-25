@Override public int size(){
  return (int)(((Producer<E>)producer).lvProducerIndex() - ((Consumer<E>)consumer).lvConsumerIndex());
}
