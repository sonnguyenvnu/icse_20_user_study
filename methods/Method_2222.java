@VisibleForTesting protected void setupRounding(final ImageRequestBuilder imageRequestBuilder,@Nullable RoundingOptions roundingOptions){
  if (roundingOptions == null) {
    return;
  }
  if (roundingOptions.isCircular()) {
    imageRequestBuilder.setImageDecodeOptions(getCircularImageDecodeOptions(roundingOptions.isAntiAliased()));
  }
}
