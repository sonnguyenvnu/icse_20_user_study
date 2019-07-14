private void subscribeTaskForRequestCancellation(final AtomicBoolean isCancelled,ProducerContext producerContext){
  producerContext.addCallbacks(new BaseProducerContextCallbacks(){
    @Override public void onCancellationRequested(){
      isCancelled.set(true);
    }
  }
);
}
