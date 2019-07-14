public static String determineFileExtension(String url,ContentTypeHeader contentTypeHeader,byte[] responseBody){
  if (contentTypeHeader.isPresent()) {
    if (contentTypeHeader.mimeTypePart().contains("json")) {
      return "json";
    }
    if (contentTypeHeader.mimeTypePart().contains("xml")) {
      return "xml";
    }
    if (contentTypeHeader.mimeTypePart().contains("text")) {
      return "txt";
    }
    String extension=COMMON_MIME_TYPES.get(contentTypeHeader.mimeTypePart());
    if (extension != null) {
      return extension;
    }
  }
  String path=URI.create(url).getPath();
  String lastPathSegment=substringAfterLast(path,"/");
  if (lastPathSegment.indexOf('.') != -1) {
    return substringAfterLast(lastPathSegment,".");
  }
  return determineTextFileExtension(stringFromBytes(responseBody,contentTypeHeader.charset()));
}
