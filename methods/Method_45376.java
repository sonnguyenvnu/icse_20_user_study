private Function<Map.Entry<String,Object>,RequestMatcher> toTargetMatcher(final Method extractorMethod){
  return new Function<Map.Entry<String,Object>,RequestMatcher>(){
    @Override @SuppressWarnings("unchecked") public RequestMatcher apply(    final Map.Entry<String,Object> pair){
      RequestExtractor extractor=createRequestExtractor(extractorMethod,pair.getKey());
      return createRequestMatcher(extractor,pair.getValue());
    }
  }
;
}
