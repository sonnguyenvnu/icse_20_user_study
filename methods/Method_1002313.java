public static int size(IndexedQueue iq){
  long after=iq.lvConsumerIndex();
  long size;
  while (true) {
    final long before=after;
    final long currentProducerIndex=iq.lvProducerIndex();
    after=iq.lvConsumerIndex();
    if (before == after) {
      size=(currentProducerIndex - after);
      break;
    }
  }
  if (size > Integer.MAX_VALUE) {
    return Integer.MAX_VALUE;
  }
 else {
    return (int)size;
  }
}
