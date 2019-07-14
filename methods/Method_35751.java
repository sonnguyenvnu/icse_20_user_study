public static Map<String,QueryParameter> splitQueryFromUrl(String url){
  String queryPart=url.contains("?") && !url.endsWith("?") ? url.substring(url.indexOf('?') + 1) : null;
  return splitQuery(queryPart);
}
