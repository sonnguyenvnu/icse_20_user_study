private Function<SimpleRestSetting,JsonResponseHandler> toJsonHandler(){
  return new Function<SimpleRestSetting,JsonResponseHandler>(){
    @Override public JsonResponseHandler apply(    final SimpleRestSetting setting){
      return JsonResponseHandler.class.cast(setting.getHandler());
    }
  }
;
}
