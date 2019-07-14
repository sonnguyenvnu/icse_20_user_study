@JsonIgnore public String getMimeType(){
  return headers == null || headers.getContentTypeHeader() == null ? OCTET_STREAM.toString() : headers.getContentTypeHeader().mimeTypePart();
}
