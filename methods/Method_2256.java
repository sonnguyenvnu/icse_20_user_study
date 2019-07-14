private static Supplier<DataSource<CloseableReference<CloseableImage>>> getImageRequestDataSourceSupplier(final ImagePipeline imagePipeline,final ImageRequest imageRequest,final Object callerContext,final ImageRequest.RequestLevel requestLevel,final RequestListener requestListener){
  return new Supplier<DataSource<CloseableReference<CloseableImage>>>(){
    @Override public DataSource<CloseableReference<CloseableImage>> get(){
      return getImageRequestDataSource(imagePipeline,imageRequest,callerContext,requestListener);
    }
  }
;
}
