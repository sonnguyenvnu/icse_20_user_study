protected DataSource<CloseableReference<CloseableImage>> fireOffRequest(FrescoState frescoState){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("FrescoControllerImpl#fireOffRequest");
  }
  DataSource<CloseableReference<CloseableImage>> dataSource;
  if (mFrescoContext.getExperiments().prepareImagePipelineComponents() && frescoState.getProducerSequence() != null && frescoState.getSettableProducerContext() != null) {
    dataSource=mFrescoContext.getImagePipeline().submitFetchRequest(frescoState.getProducerSequence(),frescoState.getSettableProducerContext(),frescoState.getRequestListener());
  }
 else {
    setupRequestListener(frescoState,frescoState.getImageRequest());
    dataSource=MultiUri.getImageRequestDataSource(mFrescoContext.getImagePipeline(),frescoState.getImageRequest(),frescoState.getCallerContext(),frescoState.getRequestListener());
  }
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return dataSource;
}
