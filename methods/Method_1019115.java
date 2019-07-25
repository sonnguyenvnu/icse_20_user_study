@Nullable private byte[] drawable(@NonNull String url) throws IOException {
  String drawableIdString=Scheme.DRAWABLE.crop(url);
  int drawableId=Integer.parseInt(drawableIdString);
  InputStream inputStream=mContext.getResources().openRawResource(drawableId);
  byte[] bytes=getBytes(inputStream);
  closeStream(inputStream);
  return bytes;
}
