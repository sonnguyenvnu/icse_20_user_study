public static QueryParameter queryParam(String key,String... values){
  return new QueryParameter(key,asList(values));
}
