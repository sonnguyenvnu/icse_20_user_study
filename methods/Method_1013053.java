public static List<Field> header(){
  return ImmutableList.of(new RefField(),new StringField("description"),new StringField("required"),new StringField("deprecated"),new StringField("allowEmptyValue"),new StringField("style"),new StringField("explode"),new StringField("allowReserved"),new ObjectField("schema"),new StringField("example"),new ObjectField("examples"),new ObjectField("content"));
}
