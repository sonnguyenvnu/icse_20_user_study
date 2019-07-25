public static List<Field> root(){
  return ImmutableList.of(new StringField("openapi",true),new InfoField(),new ArrayField("servers"),new ObjectField("paths",true),new ObjectField("components"),new ArrayField("security"),new ArrayField("tags"),new ExternalDocsField());
}
