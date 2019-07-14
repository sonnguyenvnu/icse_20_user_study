private Function<JsonResponseHandler,Object> toPojo(){
  return new Function<JsonResponseHandler,Object>(){
    @Override public Object apply(    final JsonResponseHandler handler){
      return handler.getPojo();
    }
  }
;
}
