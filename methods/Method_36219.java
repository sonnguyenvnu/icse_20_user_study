@Override public QueryParameter queryParameter(String key){
  return firstNonNull(queryParams.get(key),QueryParameter.absent(key));
}
