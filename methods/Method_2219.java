@Nullable public ImageRequest buildImageRequest(@Nullable Uri uri,ImageOptions imageOptions){
  ImageRequestBuilder builder=createImageRequestBuilder(uri,imageOptions);
  return builder != null ? builder.build() : null;
}
