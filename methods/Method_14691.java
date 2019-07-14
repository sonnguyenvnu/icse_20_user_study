static public List<JsonNode> getArray(JsonNode obj,String key){
  if (obj.has(key) && obj.get(key).getNodeType().equals(JsonNodeType.ARRAY)) {
    return Lists.newArrayList(obj.get(key).elements());
  }
 else {
    return null;
  }
}
