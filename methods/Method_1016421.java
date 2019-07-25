public boolean gzip(){
  return ((containsKey(HeaderFramework.CONTENT_ENCODING)) && ((get(HeaderFramework.CONTENT_ENCODING)).toUpperCase().startsWith("GZIP")));
}
