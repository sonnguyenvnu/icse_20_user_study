private void setAnimationUri(Uri uri){
  final PipelineDraweeControllerBuilder controllerBuilder=Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true).setOldController(mSimpleDraweeView.getController());
  if (mGifDecoder != null) {
    controllerBuilder.setImageRequest(ImageRequestBuilder.newBuilderWithSource(uri).setImageDecodeOptions(ImageDecodeOptions.newBuilder().setCustomImageDecoder(mGifDecoder).build()).build());
  }
 else {
    controllerBuilder.setUri(uri).build();
  }
  mSimpleDraweeView.setController(controllerBuilder.build());
}
