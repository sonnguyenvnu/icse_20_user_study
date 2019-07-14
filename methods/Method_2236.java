private void setupRequestListener(FrescoState frescoState,ImageRequest imageRequest){
  if (imageRequest == null) {
    return;
  }
  final RequestListener requestListener=mFrescoContext.getImagePipeline().getRequestListenerForRequest(imageRequest,frescoState.getImageOriginListener());
  frescoState.setRequestListener(requestListener);
}
