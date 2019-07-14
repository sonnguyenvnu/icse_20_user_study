static GifInfoHandle openUri(ContentResolver resolver,Uri uri) throws IOException {
  if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
    return new GifInfoHandle(uri.getPath());
  }
  final AssetFileDescriptor assetFileDescriptor=resolver.openAssetFileDescriptor(uri,"r");
  if (assetFileDescriptor == null) {
    throw new IOException("Could not open AssetFileDescriptor for " + uri);
  }
  return new GifInfoHandle(assetFileDescriptor);
}
