@Override protected void onBind(String uriString){
  Uri uri=Uri.parse(uriString);
  ImageRequestBuilder imageRequestBuilder=ImageRequestBuilder.newBuilderWithSource(uri);
  if (UriUtil.isNetworkUri(uri)) {
    imageRequestBuilder.setProgressiveRenderingEnabled(true);
  }
 else {
    imageRequestBuilder.setResizeOptions(new ResizeOptions(mImageView.getLayoutParams().width,mImageView.getLayoutParams().height));
  }
  DraweeController draweeController=Fresco.newDraweeControllerBuilder().setImageRequest(imageRequestBuilder.build()).setOldController(mImageView.getController()).setControllerListener(mImageView.getListener()).setAutoPlayAnimations(true).build();
  mImageView.setController(draweeController);
}
