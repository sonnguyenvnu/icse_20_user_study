public static String decapitalize(String string){
  return string == null ? null : string.substring(0,1).toLowerCase(Locale.ROOT) + string.substring(1);
}
