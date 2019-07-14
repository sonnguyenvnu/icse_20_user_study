@Override public void produceResults(Consumer<T> consumer,ProducerContext context){
  try {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("MultiplexProducer#produceResults");
    }
    K key=getKey(context);
    Multiplexer multiplexer;
    boolean createdNewMultiplexer;
    do {
      createdNewMultiplexer=false;
synchronized (this) {
        multiplexer=getExistingMultiplexer(key);
        if (multiplexer == null) {
          multiplexer=createAndPutNewMultiplexer(key);
          createdNewMultiplexer=true;
        }
      }
    }
 while (!multiplexer.addNewConsumer(consumer,context));
    if (createdNewMultiplexer) {
      multiplexer.startInputProducerIfHasAttachedConsumers();
    }
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
