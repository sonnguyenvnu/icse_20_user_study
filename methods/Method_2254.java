static DataSource<CloseableReference<CloseableImage>> getImageRequestDataSource(ImagePipeline imagePipeline,ImageRequest imageRequest,Object callerContext,@Nullable RequestListener requestListener){
  return imagePipeline.fetchDecodedImage(imageRequest,callerContext,requestListener);
}
