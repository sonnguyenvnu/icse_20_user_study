public static String singularize(String plural){
  if (StringUtil.isEmpty(plural))   return plural;
  if (plural.endsWith("ies")) {
    return plural.substring(0,plural.length() - 3) + "y";
  }
  if (plural.endsWith("es")) {
    return plural.substring(0,plural.length() - 2);
  }
  if (plural.endsWith("s")) {
    return plural.substring(0,plural.length() - 1);
  }
  return plural;
}
