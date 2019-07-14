private void reloadImage(Uri imageUri,@Nullable ResizeOptions resizeOptions){
  final ImageRequest imageRequest=ImageRequestBuilder.newBuilderWithSource(imageUri).setResizeOptions(resizeOptions).setImageDecodeOptions(new ImageDecodeOptionsBuilder().build()).build();
  final DraweeController draweeController=Fresco.newDraweeControllerBuilder().setOldController(mDraweeMain.getController()).setImageRequest(imageRequest).build();
  mDraweeMain.setController(draweeController);
}
