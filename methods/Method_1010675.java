public static String pluralize(String singular){
  if (StringUtil.isEmpty(singular))   return singular;
  if (singular.endsWith("y") && singular.length() > 1 && isConsonant(singular.charAt(singular.length() - 2))) {
    return singular.substring(0,singular.length() - 1) + "ies";
  }
  if (singular.endsWith("s") || singular.endsWith("x")) {
    return singular + "es";
  }
  return singular + "s";
}
