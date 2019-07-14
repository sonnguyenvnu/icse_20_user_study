private byte[] bodyDecompressedIfRequired(LoggedResponse response){
  if (response.getHeaders().getHeader(CONTENT_ENCODING).containsValue("gzip")) {
    return Gzip.unGzip(response.getBody());
  }
  return response.getBody();
}
