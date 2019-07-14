public static StringValuePattern equalToIgnoreCase(String value){
  return new EqualToPattern(value,true);
}
