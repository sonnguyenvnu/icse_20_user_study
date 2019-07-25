public static InvalidDefinitionException from(JsonGenerator g,String msg,JavaType type){
  return new InvalidDefinitionException(g,msg,type);
}
