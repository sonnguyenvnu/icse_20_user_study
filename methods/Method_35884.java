private static Function<Map.Entry<String,JsonNode>,HttpHeader> toHttpHeaders(){
  return new Function<Map.Entry<String,JsonNode>,HttpHeader>(){
    @Override public HttpHeader apply(    Map.Entry<String,JsonNode> field){
      String key=field.getKey();
      if (field.getValue().isArray()) {
        return new HttpHeader(key,ImmutableList.copyOf(transform(all(field.getValue().elements()),toStringValues())));
      }
 else {
        return new HttpHeader(key,field.getValue().textValue());
      }
    }
  }
;
}
