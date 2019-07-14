private static String percentEncode(final String value){
  return value != null ? URLs.encode(value).replace("+","%20").replace("*","%2A").replace("%7E","~") : null;
}
