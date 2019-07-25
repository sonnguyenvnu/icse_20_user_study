public static String underscore(String camelCasedWord){
  return org.apache.commons.lang.StringUtils.capitalize(camelCasedWord).replaceAll("([a-z])([A-Z])","$1_$2").toLowerCase();
}
