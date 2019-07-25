public static List<Field> parameter(){
  return ImmutableList.of(new RefField(),new StringField("name",true),new StringField("in",true),new StringField("description"),new StringField("required"),new StringField("deprecated"),new StringField("allowEmptyValue"),new StringField("style"),new StringField("explode"),new StringField("allowReserved"),new ObjectField("schema"),new StringField("example"),new ObjectField("examples"),new ObjectField("content"));
}
