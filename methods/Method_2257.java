private static Supplier<DataSource<CloseableReference<CloseableImage>>> getImageRequestDataSourceSupplier(final ImagePipeline imagePipeline,final ImageRequest imageRequest,final Object callerContext,final RequestListener requestListener){
  return getImageRequestDataSourceSupplier(imagePipeline,imageRequest,callerContext,ImageRequest.RequestLevel.FULL_FETCH,requestListener);
}
