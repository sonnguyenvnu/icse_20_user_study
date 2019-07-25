private void populate(ObjectNode node,String key,String value){
  int nextDot=key.indexOf('.');
  int nextOpenBracket=key.indexOf('[');
  boolean hasDelimiter=nextDot != -1;
  boolean hasIndexing=nextOpenBracket != -1;
  if (hasDelimiter && (!hasIndexing || (nextDot < nextOpenBracket))) {
    String fieldName=key.substring(0,nextDot);
    String remainingKey=key.substring(nextDot + 1);
    ObjectNode childNode=(ObjectNode)node.get(fieldName);
    if (childNode == null) {
      childNode=node.putObject(fieldName);
    }
    populate(childNode,remainingKey,value);
  }
 else   if (hasIndexing) {
    int nextCloseBracket=key.indexOf(']',nextOpenBracket + 1);
    if (nextCloseBracket == -1) {
      throw new IllegalArgumentException("Invalid remaining key: " + key);
    }
    String fieldName=key.substring(0,nextOpenBracket);
    int index=Integer.valueOf(key.substring(nextOpenBracket + 1,nextCloseBracket));
    String remainingKey=key.substring(nextCloseBracket + 1);
    ArrayNode arrayNode=(ArrayNode)node.get(fieldName);
    if (arrayNode == null) {
      arrayNode=node.putArray(fieldName);
    }
    padToLength(arrayNode,index + 1);
    if (remainingKey.isEmpty()) {
      arrayNode.set(index,TextNode.valueOf(value));
    }
 else     if (remainingKey.startsWith(".")) {
      remainingKey=remainingKey.substring(1);
      ObjectNode childNode;
      if (arrayNode.hasNonNull(index)) {
        childNode=(ObjectNode)arrayNode.get(index);
      }
 else {
        childNode=arrayNode.objectNode();
        arrayNode.set(index,childNode);
      }
      populate(childNode,remainingKey,value);
    }
 else {
      throw new IllegalArgumentException("Unknown key format: " + key);
    }
  }
 else {
    node.set(key,TextNode.valueOf(value));
  }
}
