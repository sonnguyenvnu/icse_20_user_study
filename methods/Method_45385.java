private ResponseHandler createCompositeHandler(final String name,final Map<String,Container> map){
  FluentIterable<ResponseHandler> handlers=from(map.entrySet()).transform(toTargetHandler(name));
  return getResponseHandler(handlers);
}
