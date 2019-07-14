static public Object loadObject(JsonNode o){
  try {
    if (o instanceof ObjectNode) {
      ObjectNode obj2=(ObjectNode)o;
      return ParsingUtilities.mapper.treeToValue(obj2,PreferenceValue.class);
    }
 else     if (o instanceof ArrayNode) {
      return o;
    }
 else {
      return ParsingUtilities.mapper.treeToValue(o,Object.class);
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
    return null;
  }
}
