public static SLanguageId deserialize(String s){
  return new SLanguageId(UUID.fromString(s));
}
