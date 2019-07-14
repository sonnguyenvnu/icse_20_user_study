private static boolean isAbsent(JsonNode rootNode){
  for (  Map.Entry<String,JsonNode> node : ImmutableList.copyOf(rootNode.fields())) {
    if (node.getKey().equals("absent")) {
      return true;
    }
  }
  return false;
}
