public static List<Field> server(){
  return ImmutableList.of(new StringField("url",true),new StringField("description"),new ObjectField("variables"));
}
