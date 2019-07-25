@Override public SchemaDigest process(final ProcessingReport report,final SchemaContext input) throws ProcessingException {
  final JsonNode schema=input.getSchema().getNode();
  final NodeType type=input.getInstanceType();
  final Map<String,JsonNode> map=Maps.newHashMap(buildDigests(schema));
  map.keySet().retainAll(typeMap.get(type));
  return new SchemaDigest(input,map);
}
