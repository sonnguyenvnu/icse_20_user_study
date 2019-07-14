private static Map<String,String> toNamespaceMap(JsonNode namespacesNode){
  ImmutableMap.Builder<String,String> builder=ImmutableMap.builder();
  for (Iterator<Map.Entry<String,JsonNode>> fields=namespacesNode.fields(); fields.hasNext(); ) {
    Map.Entry<String,JsonNode> field=fields.next();
    builder.put(field.getKey(),field.getValue().textValue());
  }
  return builder.build();
}
