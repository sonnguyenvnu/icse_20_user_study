public static List<Field> operation(){
  return ImmutableList.of(new ArrayField("tags"),new StringField("summary"),new StringField("description"),new ExternalDocsField(),new StringField("operationId"),new ArrayField("parameters"),new ObjectField("requestBody"),new ObjectField("responses",true),new ObjectField("callbacks"),new StringField("deprecated"),new ArrayField("security"),new ArrayField("servers"));
}
