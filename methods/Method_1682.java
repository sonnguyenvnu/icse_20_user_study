@Override public void produceResults(Consumer<Void> consumer,ProducerContext producerContext){
  DelegatingConsumer<T,Void> swallowResultConsumer=new DelegatingConsumer<T,Void>(consumer){
    @Override protected void onNewResultImpl(    T newResult,    @Status int status){
      if (isLast(status)) {
        getConsumer().onNewResult(null,status);
      }
    }
  }
;
  mInputProducer.produceResults(swallowResultConsumer,producerContext);
}
