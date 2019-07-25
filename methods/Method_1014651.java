public static String lookup(String uuid,String defaultName){
  String name=attributes.get(uuid);
  return name == null ? defaultName : name;
}
