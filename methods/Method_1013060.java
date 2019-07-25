public static List<Field> operation(){
  return ImmutableList.of(new ArrayField("tags"),new StringField("summary"),new StringField("description"),new ExternalDocsField(),new StringField("operationId"),new ArrayField("consumes"),new ArrayField("produces"),new ArrayField("parameters"),new ObjectField("responses",true),new ArrayField("schemes"),new StringField("deprecated"),new ArrayField("security"));
}
