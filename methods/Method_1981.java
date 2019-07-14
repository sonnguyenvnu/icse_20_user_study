private static String getThumbnailLink(final JSONObject json,final ImageSize imageSize) throws JSONException {
  Preconditions.checkNotNull(imageSize);
  final String originalUrl=json.getString("link");
  if (imageSize == ImageSize.ORIGINAL_IMAGE) {
    return originalUrl;
  }
  final int dotPos=originalUrl.lastIndexOf('.');
  final StringBuilder linkBuilder=new StringBuilder(originalUrl.length() + 1);
  return linkBuilder.append(originalUrl).insert(dotPos,imageSize.suffix).toString();
}
