private static String normalizeRequestParameters(List<HttpParameter> params){
  Collections.sort(params);
  return encodeParameters(params);
}
