protected void onNewResultImpl(@Nullable T result,int status){
  boolean isLast=BaseConsumer.isLast(status);
  if (super.setResult(result,isLast)) {
    if (isLast) {
      mRequestListener.onRequestSuccess(mSettableProducerContext.getImageRequest(),mSettableProducerContext.getId(),mSettableProducerContext.isPrefetch());
    }
  }
}
