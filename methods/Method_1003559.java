public static String capitalize(String string){
  return string == null ? null : string.substring(0,1).toUpperCase(Locale.ROOT) + string.substring(1);
}
