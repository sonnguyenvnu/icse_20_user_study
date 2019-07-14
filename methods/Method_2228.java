/** 
 * Creates imagepipeline components and sets them to frescoState 
 */
@VisibleForTesting void prepareImagePipelineComponents(FrescoState frescoState,ImageRequest imageRequest,@Nullable Object callerContext){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("FrescoControllerImpl#prepareImagePipelineComponents");
  }
  try {
    frescoState.setProducerSequence(mFrescoContext.getImagePipeline().getProducerSequenceFactory().getDecodedImageProducerSequence(imageRequest));
    ImageRequest.RequestLevel lowestPermittedRequestLevel=ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(),ImageRequest.RequestLevel.FULL_FETCH);
    setupRequestListener(frescoState,imageRequest);
    frescoState.setSettableProducerContext(new SettableProducerContext(imageRequest,mFrescoContext.getImagePipeline().generateUniqueFutureId(),frescoState.getRequestListener(),callerContext,lowestPermittedRequestLevel,false,imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()),imageRequest.getPriority()));
  }
 catch (  Exception exception) {
  }
 finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
