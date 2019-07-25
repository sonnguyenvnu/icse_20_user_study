@Override public JsonNode path(String fieldName){
  JsonNode n=_children.get(fieldName);
  if (n != null) {
    return n;
  }
  return MissingNode.getInstance();
}
