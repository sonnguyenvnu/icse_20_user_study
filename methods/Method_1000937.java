@Override public ObjectNode with(String propertyName){
  JsonNode n=_children.get(propertyName);
  if (n != null) {
    if (n instanceof ObjectNode) {
      return (ObjectNode)n;
    }
    throw new UnsupportedOperationException("Property '" + propertyName + "' has value that is not of type ObjectNode (but " + n.getClass().getName() + ")");
  }
  ObjectNode result=objectNode();
  _children.put(propertyName,result);
  return result;
}
