@Override public QueryParameter queryParameter(String key){
  return firstNonNull((splitQuery(request.getQueryString()).get(key)),QueryParameter.absent(key));
}
