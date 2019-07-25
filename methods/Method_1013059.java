public static List<Field> header(){
  return ImmutableList.of(new StringField("description"),new StringField("type",true),new StringField("format"),new ParameterItemsField(),new StringField("collectionFormat"),new StringField("default"),new StringField("maximum"),new StringField("exclusiveMaximum"),new StringField("minimum"),new StringField("exclusiveMinimum"),new StringField("maxLength"),new StringField("minLength"),new StringField("pattern"),new StringField("maxItems"),new StringField("minItems"),new StringField("uniqueItems"),new ArrayField("enum"),new StringField("multipleOf"));
}
