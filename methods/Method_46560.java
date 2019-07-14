public static boolean isTrue(String s,boolean defaultValue){
  if (s == null) {
    return defaultValue;
  }
  return ("1".equals(s) || "true".equalsIgnoreCase(s.trim()));
}
