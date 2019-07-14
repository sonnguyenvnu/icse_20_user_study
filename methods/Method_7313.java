public static String parseIntToString(String value){
  Matcher matcher=pattern.matcher(value);
  if (matcher.find()) {
    return matcher.group(0);
  }
  return null;
}
