private static String normalize(final String value){
  if (value == null) {
    return "";
  }
  return value.toLowerCase(Locale.US).replaceAll("[^a-z0-9]+","");
}
