public static List<Field> response(){
  return ImmutableList.of(new RefField(),new StringField("description"),new ObjectField("headers"),new StringField("content"),new StringField("links"));
}
