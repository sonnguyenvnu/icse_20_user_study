public static ArrayNode evaluateJsonStringToArrayNode(String parameter){
  try {
    JsonNode tree=mapper.readTree(parameter);
    if (tree instanceof ArrayNode) {
      return (ArrayNode)tree;
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
