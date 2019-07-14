private void createNotification(){
  final ImagePipeline imagePipeline=Fresco.getImagePipeline();
  final ImageRequest imageRequest=ImageRequest.fromUri(sampleUris().createSampleUri(ImageUriProvider.ImageSize.S));
  final DataSource<CloseableReference<CloseableImage>> dataSource=imagePipeline.fetchDecodedImage(imageRequest,null);
  dataSource.subscribe(new BaseBitmapDataSubscriber(){
    @Override protected void onNewResultImpl(    Bitmap bitmap){
      displayNotification(bitmap);
    }
    @Override protected void onFailureImpl(    DataSource<CloseableReference<CloseableImage>> dataSource){
      showToastText("Failed to fetch image directly: " + dataSource.getFailureCause());
      displayNotification(null);
    }
  }
,UiThreadImmediateExecutorService.getInstance());
}
