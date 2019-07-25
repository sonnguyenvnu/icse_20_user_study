public static Template compile(InputStream stream){
  try {
    return Mustache.compiler().escapeHTML(false).compile(new InputStreamReader(stream));
  }
 catch (  Exception e) {
    LOGGER.debug("Error compiling mustache template: " + e);
  }
  return null;
}
