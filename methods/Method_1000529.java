public static Header create(String properties){
  return create((Map<String,String>)Json.fromJson(properties));
}
