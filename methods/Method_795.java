public static Locale toLocale(String strVal){
  String[] items=strVal.split("_");
  if (items.length == 1) {
    return new Locale(items[0]);
  }
  if (items.length == 2) {
    return new Locale(items[0],items[1]);
  }
  return new Locale(items[0],items[1],items[2]);
}
