@Override RegexPropertyBuilder defaultProp(String name,String displayName){
  String display=(displayName + " method").trim();
  RegexPropertyBuilder prop=super.defaultProp(name.isEmpty() ? "method" : name,display);
  DESCRIPTOR_TO_DISPLAY_NAME.put(prop.getName(),display);
  return prop;
}
