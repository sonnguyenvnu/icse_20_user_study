@TargetApi(Build.VERSION_CODES.O) @Nullable protected ImageRequestBuilder createImageRequestBuilder(@Nullable Uri uri,ImageOptions imageOptions){
  if (uri == null) {
    return null;
  }
  final ImageRequestBuilder imageRequestBuilder=ImageRequestBuilder.newBuilderWithSource(uri);
  setupRounding(imageRequestBuilder,imageOptions.getRoundingOptions());
  ResizeOptions resizeOptions=imageOptions.getResizeOptions();
  if (resizeOptions != null) {
    imageRequestBuilder.setResizeOptions(resizeOptions);
  }
  imageRequestBuilder.setRequestPriority(imageOptions.getPriority());
  if (imageOptions.getBitmapConfig() != null) {
    if (imageOptions.getRoundingOptions() != null || imageOptions.getPostprocessor() != null) {
      FLog.wtf(TAG,"Trying to use bitmap config incompatible with rounding.");
    }
 else {
      imageRequestBuilder.setImageDecodeOptions(ImageDecodeOptions.newBuilder().setBitmapConfig(imageOptions.getBitmapConfig()).build());
    }
  }
  imageRequestBuilder.setLocalThumbnailPreviewsEnabled(imageOptions.areLocalThumbnailPreviewsEnabled());
  imageRequestBuilder.setShouldDecodePrefetches(getExperiments().prefetchToBitmapCache());
  imageRequestBuilder.setPostprocessor(imageOptions.getPostprocessor());
  return imageRequestBuilder;
}
