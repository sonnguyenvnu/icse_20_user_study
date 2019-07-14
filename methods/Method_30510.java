@Nullable @Override public MediaType contentType(){
  MediaType superType=super.contentType();
  if (superType != null) {
    return superType;
  }
  String mimeType=UriUtils.getType(mUri,mContentResolver);
  if (TextUtils.isEmpty(mimeType)) {
    try (InputStream inputStream=mContentResolver.openInputStream(mUri)){
      mimeType=FileTypeUtils.getImageMimeType(inputStream);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  if (!TextUtils.isEmpty(mimeType)) {
    return MediaType.parse(mimeType);
  }
  return null;
}
