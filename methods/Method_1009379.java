public static List<String> fields(Class<?> type){
  if (Builder.class.isAssignableFrom(type))   return fieldsOfMessage(messageOfBuilder(type));
  if (Message.class.isAssignableFrom(type))   return fieldsOfMessage(type);
  throw new Errors().addMessage("Invalid protocol buffer type: %s",type.getName()).toConfigurationException();
}
