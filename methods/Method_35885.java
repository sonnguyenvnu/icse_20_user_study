private static Function<JsonNode,String> toStringValues(){
  return new Function<JsonNode,String>(){
    public String apply(    JsonNode node){
      return node.textValue();
    }
  }
;
}
