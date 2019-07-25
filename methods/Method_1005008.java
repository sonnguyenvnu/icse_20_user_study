@Override public HyperLogLogPlus deserialize(final JsonParser jsonParser,final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
  TreeNode treeNode=jsonParser.getCodec().readTree(jsonParser);
  final TreeNode nestedHllp=treeNode.get("hyperLogLogPlus");
  if (nonNull(nestedHllp)) {
    treeNode=nestedHllp;
  }
  final HyperLogLogPlus hllp;
  final TextNode jsonNodes=(TextNode)treeNode.get(HyperLogLogPlusJsonConstants.HYPER_LOG_LOG_PLUS_SKETCH_BYTES_FIELD);
  if (isNull(jsonNodes)) {
    final IntNode pNode=(IntNode)treeNode.get("p");
    final IntNode spNode=(IntNode)treeNode.get("sp");
    final int p=nonNull(pNode) ? pNode.asInt(DEFAULT_P) : DEFAULT_P;
    final int sp=nonNull(spNode) ? spNode.asInt(DEFAULT_SP) : DEFAULT_SP;
    hllp=new HyperLogLogPlus(p,sp);
  }
 else {
    hllp=HyperLogLogPlus.Builder.build(jsonNodes.binaryValue());
  }
  final ArrayNode offers=(ArrayNode)treeNode.get("offers");
  if (nonNull(offers)) {
    for (    final JsonNode offer : offers) {
      if (nonNull(offer)) {
        hllp.offer(offer.asText());
      }
    }
  }
  return hllp;
}
