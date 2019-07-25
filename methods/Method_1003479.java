public static String capitalize(String string){
  return string.substring(0,1).toUpperCase(Locale.getDefault()) + string.substring(1,string.length()).toLowerCase(Locale.getDefault());
}
