public static Map<String,Object> messageToMap(Message message){
  ImmutableMap.Builder<String,Object> variablesBuilder=new ImmutableMap.Builder<>();
  message.getAllFields().forEach((field,value) -> variablesBuilder.put(UNDERSCORE_TO_CAMEL.convert(field.getName()),mapValues(field,value)));
  return variablesBuilder.build();
}
