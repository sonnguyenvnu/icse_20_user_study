@Nullable private byte[] assets(@NonNull String url) throws IOException {
  String filePath=Scheme.ASSETS.crop(url);
  InputStream inputStream=mContext.getAssets().open(filePath);
  byte[] bytes=getBytes(inputStream);
  closeStream(inputStream);
  return bytes;
}
