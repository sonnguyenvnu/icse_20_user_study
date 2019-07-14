private Predicate<SimpleRestSetting> isJsonHandlers(){
  return new Predicate<SimpleRestSetting>(){
    @Override public boolean apply(    final SimpleRestSetting setting){
      return setting.getHandler() instanceof JsonResponseHandler;
    }
  }
;
}
