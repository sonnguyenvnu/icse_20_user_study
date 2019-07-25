public static List<Field> response(){
  return ImmutableList.of(new RefField(),new StringField("description",true),new ObjectField("schema"),new ObjectField("headers"),new ObjectField("examples"));
}
