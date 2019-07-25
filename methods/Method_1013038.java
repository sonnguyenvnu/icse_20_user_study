public static List<Field> info(){
  return ImmutableList.of(new StringField("title",true),new StringField("description"),new StringField("termsOfService"),new ContactField(),new LicenseField(),new StringField("version",true));
}
