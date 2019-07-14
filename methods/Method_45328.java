public static String toBase(final String baseUri){
  if (baseUri.endsWith(SEPARATOR)) {
    return baseUri;
  }
  return baseUri + SEPARATOR;
}
