public static ObjectNode evaluateJsonStringToObjectNode(String optionsString){
  try {
    JsonNode tree=mapper.readTree(optionsString);
    if (tree instanceof ObjectNode) {
      return (ObjectNode)tree;
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
