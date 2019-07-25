public static List<Field> root(){
  return ImmutableList.of(new StringField("swagger",true),new InfoField(),new StringField("host"),new StringField("basePath"),new ArrayField("schemes"),new ArrayField("consumes"),new ArrayField("produces"),new ObjectField("paths",true),new ObjectField("definitions"),new ObjectField("parameters"),new ObjectField("responses"),new ObjectField("securityDefinitions"),new ArrayField("security"),new ArrayField("tags"),new ExternalDocsField());
}
