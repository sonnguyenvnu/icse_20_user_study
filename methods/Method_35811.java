public WireMockConfiguration useChunkedTransferEncoding(ChunkedEncodingPolicy policy){
  this.chunkedEncodingPolicy=policy;
  return this;
}
