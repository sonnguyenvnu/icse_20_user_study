@Override public boolean equals(Comparator<JsonNode> comparator,JsonNode o){
  if (!(o instanceof ArrayNode)) {
    return false;
  }
  ArrayNode other=(ArrayNode)o;
  final int len=_children.size();
  if (other.size() != len) {
    return false;
  }
  List<JsonNode> l1=_children;
  List<JsonNode> l2=other._children;
  for (int i=0; i < len; ++i) {
    if (!l1.get(i).equals(comparator,l2.get(i))) {
      return false;
    }
  }
  return true;
}
