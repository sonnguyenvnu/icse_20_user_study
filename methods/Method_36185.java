private byte[] bodyDecompressedIfRequired(Response response){
  if (response.getHeaders().getHeader("Content-Encoding").containsValue("gzip")) {
    return Gzip.unGzip(response.getBody());
  }
  return response.getBody();
}
