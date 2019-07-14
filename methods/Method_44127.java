private static Long nullSafeToLong(String str){
  try {
    return (str == null || str.isEmpty()) ? null : Long.valueOf(str);
  }
 catch (  NumberFormatException e) {
    return null;
  }
}
