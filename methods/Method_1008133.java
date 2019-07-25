public BytesReference source(IndexService indexService,DocumentMapper docMapper,Map sourceAsMap,Uid uid) throws JsonParseException, JsonMappingException, IOException {
  if (docMapper.sourceMapper().enabled() || indexService.getMetaData().isOpaqueStorage()) {
    ByteBuffer bb=(ByteBuffer)sourceAsMap.get(SourceFieldMapper.NAME);
    if (bb != null)     return new BytesArray(bb.array(),bb.position(),bb.limit() - bb.position());
  }
  XContentBuilder builder=buildDocument(docMapper,sourceAsMap,true,isStaticDocument(indexService,uid));
  builder.humanReadable(true);
  return builder.bytes();
}
