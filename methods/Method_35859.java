public static Body ofBinaryOrText(byte[] content,ContentTypeHeader contentTypeHeader){
  return new Body(content,ContentTypes.determineIsTextFromMimeType(contentTypeHeader.mimeTypePart()));
}
