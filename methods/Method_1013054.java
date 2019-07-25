public static List<Field> link(){
  return ImmutableList.of(new RefField(),new StringField("operationRef"),new StringField("operationId"),new ObjectField("parameters"),new StringField("requestBody"),new StringField("description"),new ObjectField("server"));
}
