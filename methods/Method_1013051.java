public static List<Field> encoding(){
  return ImmutableList.of(new StringField("contentType"),new ObjectField("headers"),new StringField("style"),new StringField("explode"),new StringField("allowReserved"));
}
