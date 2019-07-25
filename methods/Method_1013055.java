public static List<Field> discriminator(){
  return ImmutableList.of(new StringField("propertyName",true),new ObjectField("mapping"));
}
