@Override public void produceResults(final Consumer<EncodedImage> consumer,final ProducerContext producerContext){
  final ProducerListener listener=producerContext.getListener();
  final String requestId=producerContext.getId();
  final ImageRequest imageRequest=producerContext.getImageRequest();
  final StatefulProducerRunnable cancellableProducerRunnable=new StatefulProducerRunnable<EncodedImage>(consumer,listener,getProducerName(),requestId){
    @Override protected @Nullable EncodedImage getResult() throws Exception {
      EncodedImage encodedImage=getEncodedImage(imageRequest);
      if (encodedImage == null) {
        listener.onUltimateProducerReached(requestId,getProducerName(),false);
        return null;
      }
      encodedImage.parseMetaData();
      listener.onUltimateProducerReached(requestId,getProducerName(),true);
      return encodedImage;
    }
    @Override protected void disposeResult(    EncodedImage result){
      EncodedImage.closeSafely(result);
    }
  }
;
  producerContext.addCallbacks(new BaseProducerContextCallbacks(){
    @Override public void onCancellationRequested(){
      cancellableProducerRunnable.cancel();
    }
  }
);
  mExecutor.execute(cancellableProducerRunnable);
}
