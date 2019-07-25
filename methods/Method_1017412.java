public static ContentType create(final String mimeType,final Charset charset){
  if (mimeType == null) {
    throw new IllegalArgumentException("MIME type may not be null");
  }
  String type=mimeType.trim().toLowerCase(Locale.US);
  if (type.length() == 0) {
    throw new IllegalArgumentException("MIME type may not be empty");
  }
  if (!valid(type)) {
    throw new IllegalArgumentException("MIME type may not contain reserved characters");
  }
  return new ContentType(MimeType.valueOf(type),charset);
}
