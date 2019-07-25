protected String property(Settings settings){
  String value=settings.getProperty(PROPERTY);
  return (value == null ? "" : value.trim());
}
