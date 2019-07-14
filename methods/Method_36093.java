private String getNormalizedMappedUnder(ServletConfig config){
  String mappedUnder=config.getInitParameter(MAPPED_UNDER_KEY);
  if (mappedUnder == null) {
    return null;
  }
  if (mappedUnder.endsWith("/")) {
    mappedUnder=mappedUnder.substring(0,mappedUnder.length() - 1);
  }
  return mappedUnder;
}
