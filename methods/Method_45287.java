private <T extends SimpleRestSetting>FluentIterable<T> filter(final Iterable<RestSetting> settings,final Class<T> type,final HttpMethod method){
  return filter(settings,type).filter(isForMethod(method));
}
