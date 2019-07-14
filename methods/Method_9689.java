public static boolean supportWebview(){
  String manufacturer=Build.MANUFACTURER;
  String model=Build.MODEL;
  if ("samsung".equals(manufacturer)) {
    if ("GT-I9500".equals(model)) {
      return false;
    }
  }
  return true;
}
