public <T>DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence,SettableProducerContext settableProducerContext,RequestListener requestListener){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
  }
  try {
    return CloseableProducerToDataSourceAdapter.create(producerSequence,settableProducerContext,requestListener);
  }
 catch (  Exception exception) {
    return DataSources.immediateFailedDataSource(exception);
  }
 finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
