@Override public PipelineDraweeControllerBuilder setUri(@Nullable Uri uri){
  if (uri == null) {
    return super.setImageRequest(null);
  }
  ImageRequest imageRequest=ImageRequestBuilder.newBuilderWithSource(uri).setRotationOptions(RotationOptions.autoRotateAtRenderTime()).build();
  return super.setImageRequest(imageRequest);
}
