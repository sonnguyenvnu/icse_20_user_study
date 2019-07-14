public @Nullable static String extractMime(String path){
  String extension=extractExtension(path);
  if (extension == null) {
    return null;
  }
  extension=extension.toLowerCase(Locale.US);
  String mimeType=MimeTypeMapWrapper.getMimeTypeFromExtension(extension);
  if (mimeType == null) {
    mimeType=ADDITIONAL_ALLOWED_MIME_TYPES.get(extension);
  }
  return mimeType;
}
