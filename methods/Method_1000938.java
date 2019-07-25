@Override public boolean equals(Comparator<JsonNode> comparator,JsonNode o){
  if (!(o instanceof ObjectNode)) {
    return false;
  }
  ObjectNode other=(ObjectNode)o;
  Map<String,JsonNode> m1=_children;
  Map<String,JsonNode> m2=other._children;
  final int len=m1.size();
  if (m2.size() != len) {
    return false;
  }
  for (  Map.Entry<String,JsonNode> entry : m1.entrySet()) {
    JsonNode v2=m2.get(entry.getKey());
    if ((v2 == null) || !entry.getValue().equals(comparator,v2)) {
      return false;
    }
  }
  return true;
}
