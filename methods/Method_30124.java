@GlideType(ImageInfo.class) public static void asInfo(RequestBuilder<ImageInfo> requestBuilder){
  requestBuilder.apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
}
