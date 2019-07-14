public static RequestExtractor<String[]> query(final String param){
  return new ParamRequestExtractor(checkNotNullOrEmpty(param,"Query parameter should not be null"));
}
