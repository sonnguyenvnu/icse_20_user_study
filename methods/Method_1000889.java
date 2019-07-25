public static InvalidDefinitionException from(JsonGenerator g,String msg,BeanDescription bean,BeanPropertyDefinition prop){
  return new InvalidDefinitionException(g,msg,bean,prop);
}
