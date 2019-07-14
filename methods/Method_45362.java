private Function<ResponseSetting,ResponseHandler> toResponseHandler(){
  return new Function<ResponseSetting,ResponseHandler>(){
    @Override public ResponseHandler apply(    final ResponseSetting setting){
      return setting.getResponseHandler();
    }
  }
;
}
