public Object get(JsonNode source,String memberName){
  JsonNode propertyNode=source.get(memberName);
  if (propertyNode == null)   return null;
switch (propertyNode.getNodeType()) {
case BOOLEAN:
    return propertyNode.asBoolean();
case NUMBER:
  return propertyNode.numberValue();
case POJO:
return ((POJONode)propertyNode).getPojo();
case STRING:
return propertyNode.asText();
case BINARY:
try {
return propertyNode.binaryValue();
}
 catch (IOException ignore) {
return null;
}
case NULL:
case MISSING:
return null;
case ARRAY:
case OBJECT:
default :
return propertyNode;
}
}
