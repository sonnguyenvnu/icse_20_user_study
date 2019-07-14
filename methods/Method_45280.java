public static RestSettingBuilder all(final HttpMethod method){
  return new RestSettingBuilders(){
    @Override protected RestSetting createSetting(    final Optional<RequestMatcher> matcher,    final ResponseHandler handler){
      return new RestAllSetting(method,matcher,handler);
    }
  }
;
}
