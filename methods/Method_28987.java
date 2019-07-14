public static long toLong(String str,long defaultValue){
  if (str == null) {
    return defaultValue;
  }
  try {
    return Long.parseLong(str);
  }
 catch (  NumberFormatException nfe) {
    return defaultValue;
  }
}
