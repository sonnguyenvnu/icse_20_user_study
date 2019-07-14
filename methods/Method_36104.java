private boolean hasGzipEncoding(){
  String encodingHeader=request.getHeader("Content-Encoding");
  return encodingHeader != null && encodingHeader.contains("gzip");
}
