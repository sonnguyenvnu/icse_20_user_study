private void loadImageIntoDrawee(SimpleDraweeView draweeView,@Nullable BytesRange bytesRange){
  final ImageRequest imageRequest=ImageRequestBuilder.newBuilderWithSource(sampleUris().createSampleUri(ImageUriProvider.ImageSize.L)).setBytesRange(bytesRange).build();
  final DraweeController draweeController=Fresco.newDraweeControllerBuilder().setOldController(draweeView.getController()).setImageRequest(imageRequest).build();
  draweeView.setController(draweeController);
}
